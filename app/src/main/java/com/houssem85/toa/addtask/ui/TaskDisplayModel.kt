package com.houssem85.toa.addtask.ui

import com.houssem85.toa.tasklist.domain.model.Task

/**
 * Ui model of a [Task]
 * */
data class TaskDisplayModel(
    val description: String,
    val scheduledDate: String,
    val onTaskClicked : () -> Unit,
    val onDoneClicked : () -> Unit
)
