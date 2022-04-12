package com.houssem85.toa.tasklist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.houssem85.toa.core.ui.theme.TOATheme
import com.houssem85.toa.tasklist.domain.model.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(tasks) { task ->
            TaskListItem(
                task = task,
                onRescheduleClicked = {
                    onRescheduleClicked(task)
                },
                onDoneClicked = {
                    onDoneClicked(task)
                }
            )
        }
    }
}

@Preview
@Composable
fun TaskListPreview() {
    val tasks = (0..10).map {
        Task(
            description = "task $it"
        )
    }
    TOATheme {
        TaskList(
            tasks = tasks,
            onRescheduleClicked = {},
            onDoneClicked = {}
        )
    }
}