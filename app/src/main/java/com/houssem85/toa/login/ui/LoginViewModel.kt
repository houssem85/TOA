package com.houssem85.toa.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun signUpButtonClicked() {
        TODO()
    }

    fun loginButtonClicked() {
        val currentCredentials = _viewState.value.credentials
        viewModelScope.launch {
            _viewState.value = LoginViewState.Submitting(
                credentials = currentCredentials
            )
            when (credentialsLoginUseCase(currentCredentials)) {
                LoginResult.Failure.InvalidCredentials -> {
                    _viewState.value = LoginViewState.SubmittingError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_invalid_credentials)
                    )
                }
                LoginResult.Failure.Unknown -> {
                    _viewState.value = LoginViewState.SubmittingError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_login_failure)
                    )
                }
                LoginResult.Success -> {
                }
            }
        }
    }
}
