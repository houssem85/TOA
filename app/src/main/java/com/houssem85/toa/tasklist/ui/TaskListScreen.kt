package com.houssem85.toa.tasklist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.houssem85.toa.destinations.AddTaskScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TaskListScreen(
    navigator: DestinationsNavigator,
    viewModel: TaskListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    TaskListContent(
        viewState = viewState.value,
        onAddTaskClicked = {
            navigator.navigate(AddTaskScreenDestination)
        },
    )
}
