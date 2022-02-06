package com.houssem85.toa.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.houssem85.toa.R
import com.houssem85.toa.ui.theme.ButtonShape
import com.houssem85.toa.ui.theme.TOATheme

/**
 * This is a custom [TextButton] that provides the shape and styling expected in the
 * TOA application
 * @param[text] Text inside the button
 * @param[onClick] A callback invoked when the user clicks the button
 * @param[modifier] An optional [Modifier] to configure the composable
 **/
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.primary
) {
    val colors = textButtonColors(
        contentColor = contentColor
    )
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        shape = ButtonShape,
        colors = colors
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
private fun SecondaryButtonPreview() {
    TOATheme {
        Surface {
            SecondaryButton("hello", {})
        }
    }
}
