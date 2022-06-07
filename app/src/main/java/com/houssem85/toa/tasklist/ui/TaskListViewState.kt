package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.addtask.ui.TaskDisplayModel
import com.houssem85.toa.core.ui.UIText

sealed class TaskListViewState {
    object Loading : TaskListViewState()

    data class Active(
        val tasks: List<TaskDisplayModel>,
    ) : TaskListViewState()

    data class Error(
        val errorMessage: UIText,
    ) : TaskListViewState()
}
