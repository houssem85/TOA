@file:Suppress("MagicNumber")

package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

class DemoGetAllTasksUseCase @Inject constructor() : GetAllTasksUseCase {
    override fun invoke(): Flow<Result<List<Task>>> {
        val tasks = (1..20).map {
            Task(
                description = "task $it",
                scheduledDate = LocalDate.now()
            )
        }
        return flowOf(Result.Success(tasks))
    }
}
