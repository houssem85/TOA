package com.houssem85.toa.core.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.houssem85.toa.core.ui.theme.TOATheme

/**
 * This is our custom version of a [TextButton] that ensures the supplied [text] is capitalized.
 * */
@Composable
fun TOATextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(text = text.toUpperCase(Locale.current))
    }
}

@Preview
@Composable
private fun TOATextButtonPreview() {
    TOATheme {
        TOATextButton(
            text = "test",
            onClick = {}
        )
    }
}
