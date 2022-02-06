package com.houssem85.toa.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
 * */
@Composable
fun LoginContent(
    viewState: LoginViewState,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.ic_toa_checkmark),
                contentDescription = stringResource(R.string.app_logo_content_description),
                modifier = Modifier.fillMaxWidth(APP_LOGO_WIDTH_PERCENTAGE)
            )
            Spacer(modifier = Modifier.weight(1F))
            TOATextField(text = viewState.userName, onTextChanged = {

            }, labelText = stringResource(R.string.username))
            VerticalSpacer(height = 12.dp)
            TOATextField(text = viewState.password, onTextChanged = {

            }, labelText = stringResource(R.string.password))
            VerticalSpacer(height = 48.dp)
            PrimaryButton(
                text = stringResource(R.string.log_in),
                onClick = {

                },
            )
            SecondaryButton(
                text = stringResource(R.string.sign_up),
                onClick = {

                },
            )
        }
    }
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
        LoginContent(viewState)
    }
}
