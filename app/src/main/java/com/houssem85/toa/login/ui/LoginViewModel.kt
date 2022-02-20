package com.houssem85.toa.login.ui

import androidx.lifecycle.ViewModel
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.Password
import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This view model emit the view state to login screen based on the result of credentials login use case,
 * handle the ui logic
 * @param[credentialsLoginUseCase] present the use case of login with credentials.
 * */
class LoginViewModel(
    private val credentialsLoginUseCase: CredentialsLoginUseCase,
) : ViewModel() {

    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(value: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.copy(
                email = Email(value)
            )
        )
    }

    fun passwordChanged(value: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.copy(
                password = Password(value)
            )
        )
    }

    fun signInButtonClicked() {
        TODO()
    }

    fun loginButtonClicked() {
        TODO()
    }
}
