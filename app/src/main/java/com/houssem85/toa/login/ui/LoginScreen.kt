package com.houssem85.toa.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val viewState = viewModel.viewState.collectAsState()
    LoginContent(
        viewState = viewState.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = viewModel::loginButtonClicked,
        onSignUpClicked = viewModel::signUpButtonClicked
    )
}
