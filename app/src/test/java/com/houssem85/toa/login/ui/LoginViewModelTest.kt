package com.houssem85.toa.login.ui

import com.houssem85.toa.MainDispatcherRule
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testUpdateCredentials() = runTest {
        val standardTestDispatcher = StandardTestDispatcher()
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

        testRobot.buildViewModel(standardTestDispatcher)
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.enterEmail(testEmail)
                testRobot.enterPassword(testPassword)
            },
            expectedViewStates
        )
    }

    @Test
    fun testSubmitInvalidCredentials() = runTest {
        val testDispatcher = StandardTestDispatcher()

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

        testRobot.mockLoginResultForCredentials(
            Credentials(
                email = Email(testEmail),
                password = Password(testPassword),
            ),
            LoginResult.Failure.InvalidCredentials
        )

        testRobot.buildViewModel(testDispatcher)
        testRobot.assertViewState(initialState)
        testRobot.enterEmail(testEmail)
        testRobot.assertViewState(emailEnteredState)
        testRobot.enterPassword(testPassword)
        testRobot.assertViewState(passwordEmailEnteredState)

        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.clickLoginButton()
                advanceUntilIdle()
            },
            listOf(passwordEmailEnteredState, submittingState, invalidCredentialsState)
        )
    }

    @Test
    fun testUnknownLoginFailure() = runTest {
        val standardTestDispatcher = StandardTestDispatcher()
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
        testRobot.buildViewModel(standardTestDispatcher)
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
                advanceUntilIdle()
            },
            expectedViewStates = expectedViewStates,
        )
    }

    @Test
    fun testSubmitWithoutCredentials() = runTest {
        val standardTestDispatcher = StandardTestDispatcher()
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
        testRobot.buildViewModel(standardTestDispatcher)
        testRobot.assertViewStatesAfterAction(
            action = {
                testRobot.clickLoginButton()
                advanceUntilIdle()
            },
            expectedViewStates = expectedViewStates
        )
    }
}
