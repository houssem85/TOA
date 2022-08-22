package com.houssem85.toa.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.houssem85.toa.destinations.LoginScreenDestination
import com.houssem85.toa.destinations.TaskListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val viewState = loginViewModel.viewState.collectAsState()

    LaunchedEffect(viewState.value){
        navigator.navigate(TaskListScreenDestination) {
            this.popUpTo(LoginScreenDestination.route) {
                inclusive = true
            }
        }
    }

    LoginContent(
        viewState = viewState.value,
        onEmailChanged = loginViewModel::emailChanged,
        onPasswordChanged = loginViewModel::passwordChanged,
        onLoginClicked = loginViewModel::loginButtonClicked,
        onSignUpClicked = loginViewModel::signUpButtonClicked
    )
}
