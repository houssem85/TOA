package com.houssem85.toa.addtask.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class ProdAddTaskUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository,
) : AddTaskUseCase {
    override suspend fun invoke(task: Task): Result<Unit> {
        return taskListRepository.addTask(task)
    }
}
