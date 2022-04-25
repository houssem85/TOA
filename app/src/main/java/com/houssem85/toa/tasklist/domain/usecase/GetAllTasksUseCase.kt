package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task

interface GetAllTasksUseCase {
    suspend operator fun invoke(): Result<List<Task>>
}
