package com.houssem85.toa.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val viewState = loginViewModel.viewState.collectAsState()
    LoginContent(
        viewState = viewState.value,
        onEmailChanged = loginViewModel::emailChanged,
        onPasswordChanged = loginViewModel::passwordChanged,
        onLoginClicked = loginViewModel::loginButtonClicked,
        onSignUpClicked = loginViewModel::signUpButtonClicked
    )
}
