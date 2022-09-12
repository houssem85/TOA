package com.houssem85.toa.fakes

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow

class FakeTaskListRepository {
    val mock: TaskListRepository = mockk()

    fun mockFetchAllTasksResult(result: Flow<Result<List<Task>>>) {
        coEvery {
            mock.fetchAllTasks()
        } returns result
    }
}
