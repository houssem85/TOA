@file:Suppress("MagicNumber")
package com.houssem85.toa.tasklist.data.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository

class DemoTaskListRepository : TaskListRepository {
    override suspend fun fetchAllTasks(): Result<List<Task>> {
        val tasks = (1..10).map {
            Task(
                description = "task $it"
            )
        }
        return Result.Success(tasks)
    }

    override suspend fun addTask(task: Task): Result<Unit> {
        return Result.Success(Unit)
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        return Result.Success(Unit)
    }

    override suspend fun markAsComplete(task: Task): Result<Unit> {
        return Result.Success(Unit)
    }
}
