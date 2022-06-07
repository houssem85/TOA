package com.houssem85.toa.addtask.ui

import com.houssem85.toa.tasklist.domain.model.Task
import java.time.format.DateTimeFormatter

/**
 * Ui model of a [Task]
 * */
data class TaskDisplayModel(
    val description: String,
    val scheduledDate: String,
)

fun Task.toTaskDisplayModel(): TaskDisplayModel {
    val friendlyDatePattern = "MMM dd, yyyy"
    val friendlyDateFormatter = DateTimeFormatter.ofPattern(friendlyDatePattern)
    return TaskDisplayModel(
        description = this.description,
        scheduledDate = friendlyDateFormatter.format(this.scheduledDate),
    )
}
