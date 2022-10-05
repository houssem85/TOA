package com.houssem85.toa.tasklist.domain.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias TaskListResult = Result<List<Task>>

interface TaskListRepository {


    fun fetchAllTasks(): Flow<TaskListResult>

    /**
     * get tasks for a given date
     * */
    fun fetchTasksForDate(date: LocalDate): Flow<TaskListResult>

    /**
     * Add a new [task] and return a [Result] that wrap success or failure.
     * */
    suspend fun addTask(task: Task): Result<Unit>

    /**
     * Delete an existing task.
     * */
    suspend fun deleteTask(task: Task): Result<Unit>

    /**
     * Mark [task] is complete.
     * */
    suspend fun markAsComplete(task: Task): Result<Unit>
}
