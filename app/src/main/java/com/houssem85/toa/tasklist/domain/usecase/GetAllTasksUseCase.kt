package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTasksUseCase {
    operator fun invoke(): Flow<Result<List<Task>>>
}
