package com.houssem85.toa.addtask.ui

import com.houssem85.toa.tasklist.domain.model.Task

/**
 * Ui model of a [Task]
 * */
data class TaskDisplayModel(
    val description: String,
    val scheduledDate: String,
    val onTaskClicked: () -> Unit,
    val onDoneClicked: () -> Unit,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as TaskDisplayModel

        if (description != other.description || scheduledDate != other.scheduledDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + scheduledDate.hashCode()
        return result
    }
}
