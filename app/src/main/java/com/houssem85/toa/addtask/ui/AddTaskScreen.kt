package com.houssem85.toa.addtask.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    AddTaskContent(
        viewState = viewState.value,
        onDescriptionChanged = {
        },
        onScheduledDateChanged = viewModel::onTaskScheduledDateChanged,
        onSubmitClicked = {
        }
    )
}
