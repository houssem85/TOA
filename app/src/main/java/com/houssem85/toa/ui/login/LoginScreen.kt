package com.houssem85.toa.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.houssem85.toa.R
import com.houssem85.toa.ui.components.PrimaryButton
import com.houssem85.toa.ui.components.SecondaryButton
import com.houssem85.toa.ui.components.TOATextField
import com.houssem85.toa.ui.core.VerticalSpacer
import com.houssem85.toa.ui.theme.TOATheme

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.75F

/**
 * This composable maintains the entire screen for handling user login.
 * @param[viewState] The current state of the screen to render.
 * @param[onUserNameChanged] A callback that invoked when the user change text in [UsernameInput]
 * @param[onPasswordChanged] A callback that invoked when the user change text in [PasswordInput]
 * @param[onLoginClicked] A callback that invoked when the user clicks [LoginButton]
 * @param[onSignUpClicked] A callback that invoked when the user clicks [SignUpButton]
 * */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            AppLogo()
            Spacer(modifier = Modifier.weight(1F))
            UsernameInput(viewState.userName, onTextChanged = onUserNameChanged)
            VerticalSpacer(height = 12.dp)
            PasswordInput(viewState.password, onTextChanged = onPasswordChanged)
            VerticalSpacer(height = 48.dp)
            LoginButton(onClick = onLoginClicked)
            SignUpButton(onClick = onSignUpClicked)
        }
    }
}

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick,
    )
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(
        text = stringResource(R.string.log_in),
        onClick = onClick,
    )
}

@Composable
private fun PasswordInput(text: String, onTextChanged: (String) -> Unit) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password)
    )
}

@Composable
private fun UsernameInput(text: String, onTextChanged: (String) -> Unit) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.username)
    )
}

@Composable
private fun AppLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_toa_checkmark),
        contentDescription = stringResource(R.string.app_logo_content_description),
        modifier = Modifier.fillMaxWidth(APP_LOGO_WIDTH_PERCENTAGE)
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun LoginContentPreview() {
    TOATheme {
        val viewState = LoginViewState("", "")
        LoginContent(viewState, {
        }, {
        }, {
        }, {
        })
    }
}
