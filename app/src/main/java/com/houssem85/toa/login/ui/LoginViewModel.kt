package com.houssem85.toa.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import com.houssem85.toa.login.ui.LoginViewState.Active
import com.houssem85.toa.login.ui.LoginViewState.Initial
import com.houssem85.toa.login.ui.LoginViewState.Submitting
import com.houssem85.toa.login.ui.LoginViewState.SubmittingError
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
        MutableStateFlow(Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(value: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = Active(
            credentials = currentCredentials.copy(
                email = Email(value)
            )
        )
    }

    fun passwordChanged(value: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = Active(
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
            _viewState.value = Submitting(
                credentials = currentCredentials
            )
            val loginResult = credentialsLoginUseCase(currentCredentials)
            _viewState.value = when (loginResult) {
                is LoginResult.Failure.InvalidCredentials -> {
                    SubmittingError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_invalid_credentials)
                    )
                }
                is LoginResult.Failure.Unknown -> {
                    SubmittingError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_login_failure)
                    )
                }
                is LoginResult.Failure.EmptyCredentials -> {
                    loginResult.toLoginViewState(
                        credentials = currentCredentials
                    )
                }
                is LoginResult.Success -> {
                    _viewState.value
                }
            }
        }
    }
}

private fun LoginResult.Failure.EmptyCredentials.toLoginViewState(
    credentials: Credentials
): LoginViewState {
    return Active(
        credentials = credentials,
        emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email).takeIf {
            emptyEmail
        },
        passwordInputErrorMessage =
        UIText.ResourceText(R.string.err_empty_password).takeIf {
            emptyPassword
        }
    )
}
