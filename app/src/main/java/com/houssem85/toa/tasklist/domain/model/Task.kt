package com.houssem85.toa.tasklist.domain.model

import java.time.LocalDate

data class Task(
    val id: String = "",
    val description: String,
    val scheduledDate: LocalDate,
)
