package com.houssem85.toa.addtask.domain.usecase

import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.tasklist.domain.model.Task

interface AddTaskUseCase {
    suspend operator fun invoke(task: Task): AddTaskResult
}
