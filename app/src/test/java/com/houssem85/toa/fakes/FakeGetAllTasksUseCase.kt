package com.houssem85.toa.fakes

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow

class FakeGetAllTasksUseCase {

    val mock: GetAllTasksUseCase = mockk()

    fun mockResult(result: Flow<Result<List<Task>>>) {
        coEvery {
            mock()
        } returns result
    }
}
