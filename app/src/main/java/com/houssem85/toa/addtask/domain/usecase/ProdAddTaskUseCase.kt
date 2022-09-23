package com.houssem85.toa.addtask.domain.usecase

import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import java.time.LocalDate
import javax.inject.Inject

class ProdAddTaskUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository,
) : AddTaskUseCase {
    override suspend fun invoke(task: Task): AddTaskResult {
        val failureResult = validateInput(task)
        if (failureResult != null) {
            return failureResult
        }
        val result = taskListRepository.addTask(task)
        return when (result) {
            is Result.Error -> AddTaskResult.Failure.Unknown
            is Result.Success -> AddTaskResult.Success
        }
    }

    private fun validateInput(task: Task): AddTaskResult.Failure.InvalidInput? {
        val emptyDescription = task.description.isEmpty()
        val scheduledDateInPast = task.scheduledDate.isBefore(LocalDate.now())
        return if (emptyDescription || scheduledDateInPast) {
            AddTaskResult.Failure.InvalidInput(
                emptyDescription = emptyDescription,
                scheduledDateInPast = scheduledDateInPast
            )
        } else {
            null
        }
    }
}
