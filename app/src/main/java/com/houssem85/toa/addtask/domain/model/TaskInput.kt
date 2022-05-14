package com.houssem85.toa.addtask.domain.model

import java.time.LocalDate

/**
 * This entity represent the information's required to create a task.
 * */
data class TaskInput(
    val description: String = "",
    val scheduledDate: LocalDate = LocalDate.now()
)
