package com.houssem85.toa.fakes

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.usecase.RescheduleTaskUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeRescheduleTaskUseCase {

    val mock: RescheduleTaskUseCase = mockk()

    fun mockRescheduleTaskResult(taskId : String,result: Result<Unit>) {
        coEvery {
            mock(taskId)
        } returns result
    }
}