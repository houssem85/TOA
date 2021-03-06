package com.houssem85.toa.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.core.ui.components.PrimaryButton
import com.houssem85.toa.core.ui.components.SecondaryButton
import com.houssem85.toa.core.ui.components.TOATextField
import com.houssem85.toa.core.ui.components.VerticalSpacer
import com.houssem85.toa.core.ui.getString
import com.houssem85.toa.core.ui.theme.TOATheme
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.Password

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.75F

/**
 * This composable maintains the entire screen for handling user login.
 * @param[viewState] The current state of the screen to render.
 * @param[onEmailChanged] A callback that invoked when the user change text in [EmailInput]
 * @param[onPasswordChanged] A callback that invoked when the user change text in [PasswordInput]
 * @param[onLoginClicked] A callback that invoked when the user clicks [LoginButton]
 * @param[onSignUpClicked] A callback that invoked when the user clicks [SignUpButton]
 * */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.screen_padding)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1F))
                AppLogo()
                Spacer(modifier = Modifier.weight(1F))
                EmailInput(
                    text = viewState.credentials.email.value,
                    onTextChanged = onEmailChanged,
                    errorMessage = (viewState as? LoginViewState.Active)?.emailInputErrorMessage?.getString(
                        LocalContext.current
                    ),
                    enabled = viewState.inputsEnabled
                )
                VerticalSpacer(height = 12.dp)
                PasswordInput(
                    text = viewState.credentials.password.value,
                    onTextChanged = onPasswordChanged,
                    errorMessage = (viewState as? LoginViewState.Active)?.passwordInputErrorMessage?.getString(
                        LocalContext.current
                    ),
                    enabled = viewState.inputsEnabled,
                )
                if (viewState is LoginViewState.SubmittingError) {
                    Text(
                        text = viewState.errorMessage.getString(LocalContext.current),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }
                VerticalSpacer(height = 48.dp)
                LoginButton(
                    onClick = onLoginClicked,
                    enabled = viewState.inputsEnabled,
                )
                SignUpButton(
                    onClick = onSignUpClicked,
                    enabled = viewState.inputsEnabled,
                )
            }
            if (viewState is LoginViewState.Submitting) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    enabled: Boolean,
) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    enabled: Boolean,
) {
    PrimaryButton(
        text = stringResource(R.string.log_in),
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?,
    enabled: Boolean,
) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password),
        errorMessage = errorMessage,
        visualTransformation = PasswordVisualTransformation(
            '-'
        ),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

@Composable
private fun EmailInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?,
    enabled: Boolean,
) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.email),
        errorMessage = errorMessage,
        enabled = enabled
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
private fun LoginContentPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    loginViewState: LoginViewState,
) {
    TOATheme {
        LoginContent(loginViewState, {
        }, {
        }, {
        }, {
        })
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LoginViewState> {
    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                Email("test@testface.com"),
                Password("Hunter2"),
            )
            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmittingError(
                    credentials = activeCredentials,
                    errorMessage = UIText.ResourceText(R.string.err_invalid_credentials)
                )
            )
        }
}
