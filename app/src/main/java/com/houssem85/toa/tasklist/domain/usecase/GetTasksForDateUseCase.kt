package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetTasksForDateUseCase {
    operator fun invoke(date: LocalDate): Flow<TaskListResult>
}