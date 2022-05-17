package com.houssem85.toa.addtask.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task

interface MarkTaskAsCompleteUseCase {

    suspend fun invoke(task: Task): Result<Unit>
}
