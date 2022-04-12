package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task

sealed class TaskListViewState {
    object Loading : TaskListViewState()

    data class Active(
        val tasks: List<Task>,
    ) : TaskListViewState()

    data class Error(
        val errorMessage: UIText,
    ) : TaskListViewState()
}
