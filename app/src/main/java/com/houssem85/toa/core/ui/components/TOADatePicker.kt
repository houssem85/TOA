package com.houssem85.toa.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.houssem85.toa.core.ui.theme.TOATheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A custom composable what show a date picker when clicked
 * */
@Composable
fun TOADatePicker(
    value: LocalDate,
    modifier: Modifier,
    onDateSelected: (LocalDate) -> Unit,
    errorMessage: String? = null,
) {

    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = value
        ) { date ->
            onDateSelected(date)
        }
    }
    Column {
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(50)
                )
                .padding(12.dp)
                .clickable {
                    dialogState.show()
                }
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = value.toUIString()
            )
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Select Date"
            )
        }

        if (errorMessage != null) {
            androidx.compose.material.Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 16.dp,
                )
            )
        }
    }
}

private fun LocalDate.toUIString(): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    return formatter.format(this)
}

@Preview(
    name = "Day mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun TOADatePickerPreview() {
    TOATheme {
        Surface {
            TOADatePicker(
                LocalDate.now(),
                modifier = Modifier.fillMaxWidth(),
                onDateSelected = {
                },
                "error message"
            )
        }
    }
}
