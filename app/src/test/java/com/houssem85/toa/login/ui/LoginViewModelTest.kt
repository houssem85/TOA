package com.houssem85.toa.login.ui

import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.Password
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

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
}
