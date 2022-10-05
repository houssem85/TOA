package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import com.houssem85.toa.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class ProdGetTasksForDateUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository,
) : GetTasksForDateUseCase {
    override fun invoke(date: LocalDate): Flow<TaskListResult> {
        return taskListRepository.fetchTasksForDate(date)
    }
}