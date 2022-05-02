package com.houssem85.toa.fakes

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeGetAllTasksUseCase {

    val mock: GetAllTasksUseCase = mockk()

    fun mockResult(result: Result<List<Task>>) {
        coEvery {
            mock()
        } returns result
    }
}
