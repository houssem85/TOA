package com.houssem85.toa.login.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.fakes.FakeCredentialsLoginUseCase

class LoginViewModelRobot {

    private val credentialsLoginUseCase = FakeCredentialsLoginUseCase()
    private lateinit var viewModel: LoginViewModel

    fun buildViewModel() {
        viewModel = LoginViewModel(
            credentialsLoginUseCase = credentialsLoginUseCase.mock,
        )
    }

    fun enterEmail(
        email: String
    ) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(
        password: String
    ) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.loginButtonClicked()
    }

    fun clickSignInButton() = apply {
        viewModel.signInButtonClicked()
    }

    suspend fun assertViewStatesAfterAction(
        action: LoginViewModelRobot.() -> Unit,
        expectedViewStates: List<LoginViewState>
    ) {
        viewModel.viewState.test {
            action()
            for (state in expectedViewStates) {
                assertThat(awaitItem()).isEqualTo(state)
            }
            this.cancel()
        }
    }
}
