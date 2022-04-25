package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class ProdGetAllTasksUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository,
) : GetAllTasksUseCase {
    override suspend fun invoke(): Result<List<Task>> {
        return taskListRepository.fetchAllTasks()
    }
}
