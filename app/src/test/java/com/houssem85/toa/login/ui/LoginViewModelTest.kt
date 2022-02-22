package com.houssem85.toa.login.ui

import com.houssem85.toa.CoroutinesTestRule
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testUpdateCredentials() = runBlockingTest {
        val testEmail = "test@toa.com"
        val testPassword = "0000"

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail)
            )
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )

        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState
        )

        testRobot.buildViewModel()
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.enterEmail(testEmail)
                testRobot.enterPassword(testPassword)
            },
            expectedViewStates
        )
    }

    @Test
    fun testSubmitInvalidCredentials() = runBlockingTest {
        val testEmail = "wrong@toa.com"
        val testPassword = "0000"

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail)
            )
        )
        val passwordEmailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )
        val submittingState = LoginViewState.Submitting(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )
        val invalidCredentialsState = LoginViewState.SubmittingError(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            UIText.ResourceText(R.string.err_invalid_credentials),
        )
        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            passwordEmailEnteredState,
            submittingState,
            invalidCredentialsState,
        )
        testRobot.buildViewModel()
        testRobot.mockLoginResultForCredentials(
            Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            LoginResult.Failure.InvalidCredentials
        )
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.enterEmail(testEmail)
                testRobot.enterPassword(testPassword)
                testRobot.clickLoginButton()
            },
            expectedViewStates = expectedViewStates,
        )
    }

    @Test
    fun testUnknownLoginFailure() = runBlockingTest {
        val testEmail = "wrong@toa.com"
        val testPassword = "0000"

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail)
            )
        )
        val passwordEmailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )
        val submittingState = LoginViewState.Submitting(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )
        val unknownErrorState = LoginViewState.SubmittingError(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            UIText.ResourceText(R.string.err_login_failure),
        )
        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            passwordEmailEnteredState,
            submittingState,
            unknownErrorState,
        )
        testRobot.buildViewModel()
        testRobot.mockLoginResultForCredentials(
            Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            LoginResult.Failure.Unknown
        )
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.enterEmail(testEmail)
                testRobot.enterPassword(testPassword)
                testRobot.clickLoginButton()
            },
            expectedViewStates = expectedViewStates,
        )
    }

    @Test
    fun testSubmitWithoutCredentials() = runBlockingTest {

        val testEmail = ""
        val testPassword = ""

        val initialState = LoginViewState.Initial

        val submittingState = LoginViewState.Submitting(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            )
        )
        val activeStateWithInputError = LoginViewState.Active(
            credentials = Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password),
        )

        val expectedViewStates = listOf(
            initialState,
            submittingState,
            activeStateWithInputError,
        )
        testRobot.mockLoginResultForCredentials(
            Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            LoginResult.Failure.EmptyCredentials(
                emptyEmail = true,
                emptyPassword = true,
            )
        )
        testRobot.buildViewModel()
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.clickLoginButton()
            },
            expectedViewStates = expectedViewStates
        )
    }
}
