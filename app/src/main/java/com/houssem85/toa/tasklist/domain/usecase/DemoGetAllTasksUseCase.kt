@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import javax.inject.Inject

class DemoGetAllTasksUseCase @Inject constructor() : GetAllTasksUseCase {
    override suspend fun invoke(): Result<List<Task>> {
        val tasks = (1..20).map {
            Task(
                description = "task $it"
            )
        }
        return Result.Success(tasks)
    }
}
