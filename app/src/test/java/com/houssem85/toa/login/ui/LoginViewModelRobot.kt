package com.houssem85.toa.login.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.fakes.FakeCredentialsLoginUseCase
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResult
import kotlinx.coroutines.CoroutineDispatcher

class LoginViewModelRobot {

    private val credentialsLoginUseCase = FakeCredentialsLoginUseCase()
    private lateinit var viewModel: LoginViewModel

    fun buildViewModel(coroutineDispatcher: CoroutineDispatcher) {
        viewModel = LoginViewModel(
            credentialsLoginUseCase = credentialsLoginUseCase.mock,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    fun enterEmail(
        email: String,
    ) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(
        password: String,
    ) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.loginButtonClicked()
    }

    fun clickSignUpButton() = apply {
        viewModel.signUpButtonClicked()
    }

    suspend fun assertViewStatesAfterAction(
        action: LoginViewModelRobot.() -> Unit,
        expectedViewStates: List<LoginViewState>,
    ) {
        viewModel.viewState.test {
            action()
            for (state in expectedViewStates) {
                val awaitItem = awaitItem()
                assertThat(awaitItem).isEqualTo(state)
            }
            this.cancel()
        }
    }

    fun assertViewState(
        expectedViewState: LoginViewState,
    ) {
        assertThat(viewModel.viewState.value).isEqualTo(expectedViewState)
    }

    fun mockLoginResultForCredentials(credentials: Credentials, loginResult: LoginResult) {
        credentialsLoginUseCase.mockLoginResultForCredentials(
            credentials = credentials,
            loginResult = loginResult,
        )
    }
}
