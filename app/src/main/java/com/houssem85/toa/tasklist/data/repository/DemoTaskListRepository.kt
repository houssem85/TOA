@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.data.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

class DemoTaskListRepository @Inject constructor() : TaskListRepository {

    private val tasks = (1..10).map {
        Task(
            description = "task $it",
            scheduledDate = LocalDate.now()
        )
    }.toMutableList()

    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
        return flowOf(Result.Success(tasks))
    }

    override suspend fun addTask(task: Task): Result<Unit> {
        tasks.add(task)
        return Result.Success(Unit)
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        tasks.remove(task)
        return Result.Success(Unit)
    }

    override suspend fun markAsComplete(task: Task): Result<Unit> {
        return Result.Success(Unit)
    }
}
