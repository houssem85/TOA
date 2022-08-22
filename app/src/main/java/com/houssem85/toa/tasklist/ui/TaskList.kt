@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.houssem85.toa.addtask.ui.TaskDisplayModel
import com.houssem85.toa.core.ui.theme.TOATheme

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<TaskDisplayModel>,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(tasks) { task ->
            TaskListItem(
                task = task,
            )
        }
    }
}

@Preview
@Composable
fun TaskListPreview() {
    val tasks = (0..10).map {
        TaskDisplayModel(
            description = "task $it",
            scheduledDate = "scheduledDate",
            onTaskClicked = {
            },
            onDoneClicked = {
            }
        )
    }
    TOATheme {
        TaskList(
            tasks = tasks,
        )
    }
}
