@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import java.time.LocalDate
import javax.inject.Inject

class DemoGetAllTasksUseCase @Inject constructor() : GetAllTasksUseCase {
    override suspend fun invoke(): Result<List<Task>> {
        val tasks = (1..20).map {
            Task(
                description = "task $it",
                scheduledDate = LocalDate.now()
            )
        }
        return Result.Success(tasks)
    }
}
