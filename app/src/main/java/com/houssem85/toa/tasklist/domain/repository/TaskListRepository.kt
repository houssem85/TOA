package com.houssem85.toa.tasklist.domain.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task

interface TaskListRepository {
    suspend fun fetchAllTasks(): Result<List<Task>>
}
