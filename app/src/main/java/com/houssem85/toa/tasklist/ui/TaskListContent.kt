package com.houssem85.toa.tasklist.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.houssem85.toa.core.ui.theme.TOATheme
import com.houssem85.toa.tasklist.domain.model.Task

@Composable
fun TaskListContent(
    viewState: TaskListViewState,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    onAddTaskClicked: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            if (viewState is TaskListViewState.Active) {
                AddTaskButton(onAddTaskClicked)
            }
        }
    ) {
        if (viewState is TaskListViewState.Active) {
            TaskList(
                tasks = viewState.tasks,
                onRescheduleClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked,
            )
        }
    }
}

@Composable
private fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Add"
        )
    }
}

@Preview
@Composable
fun TaskListContentPreview() {
    val tasks = (0..10).map {
        Task(
            description = "task $it"
        )
    }
    val viewState = TaskListViewState.Active(tasks)
    TOATheme {
        TaskListContent(
            viewState = viewState,
            onRescheduleClicked = {
            },
            onDoneClicked = {
            },
            onAddTaskClicked = {
            }
        )
    }
}
