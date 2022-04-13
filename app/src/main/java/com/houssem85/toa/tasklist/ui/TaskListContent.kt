@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            AddTaskButton(onAddTaskClicked)
        },
        topBar = {
            TaskListToolbar()
        }
    ) { paddingValues ->
        if (viewState is TaskListViewState.Active) {
            TaskList(
                modifier = Modifier.padding(paddingValues),
                tasks = viewState.tasks,
                onRescheduleClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked,
            )
        }
    }
}

@Composable
private fun TaskListToolbar() {
    Surface(
        color = MaterialTheme.colors.primary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(84.dp)
        ) {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "TODO",
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = "Today",
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "TODO",
                    modifier = Modifier.size(40.dp)
                )
            }
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
