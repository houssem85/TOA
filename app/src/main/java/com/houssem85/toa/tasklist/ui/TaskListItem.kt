package com.houssem85.toa.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.houssem85.toa.R
import com.houssem85.toa.core.ui.components.TOATextButton
import com.houssem85.toa.core.ui.theme.TOATheme
import com.houssem85.toa.tasklist.domain.model.Task

/**
 * this display a list item for a given task.
 * */
@Composable
fun TaskListItem(
    task: Task,
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit,
) {
    Card {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            TaskText(task.description)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                RescheduleButton(onClick = onRescheduleClicked)
                DoneButton(onClick = onDoneClicked)
            }
        }
    }
}

@Composable
private fun RescheduleButton(onClick: () -> Unit) {
    TOATextButton(
        text = stringResource(R.string.reschedule),
        onClick = onClick
    )
}

@Composable
private fun DoneButton(onClick: () -> Unit) {
    TOATextButton(
        text = stringResource(R.string.done),
        onClick = onClick
    )
}

@Composable
private fun TaskText(text: String) {
    Text(text = text)
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun TaskListItemPreview() {
    TOATheme {
        val task = Task(
            description = "test description"
        )
        TaskListItem(
            task,
            onDoneClicked = {
            },
            onRescheduleClicked = {
            }
        )
    }
}
