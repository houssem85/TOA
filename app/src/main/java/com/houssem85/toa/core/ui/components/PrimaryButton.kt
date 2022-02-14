package com.houssem85.toa.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.theme.ButtonShape
import com.houssem85.toa.core.ui.theme.TOATheme

/**
 * This is a custom [Button] that provides the shape and styling expected in the
 * TOA application
 * @param[text] Text inside the button
 * @param[onClick] A callback invoked when the user clicks the button
 * @param[modifier] An optional [Modifier] to configure the composable
 * @param[backgroundColor] The color of the button in enabled state
 * @param[enabled] If its true the button become clickable otherwise not.
 **/
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    enabled: Boolean = true
) {
    val buttonColors = buttonColors(
        backgroundColor = backgroundColor
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        colors = buttonColors,
        shape = ButtonShape,
        enabled = enabled
    ) {
        Text(
            text = text.toUpperCase(Locale.current)
        )
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
private fun PrimaryButtonPreview() {
    TOATheme {
        PrimaryButton("hello", {})
    }
}
