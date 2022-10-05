package com.houssem85.toa.fakes

import com.houssem85.toa.tasklist.domain.repository.TaskListResult
import com.houssem85.toa.tasklist.domain.usecase.GetTasksForDateUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class FakeGetTasksForDateUseCase {

    val mock: GetTasksForDateUseCase = mockk()

    fun mockResult(date: LocalDate, result: Flow<TaskListResult>) {
        coEvery {
            mock.invoke(date)
        } returns result
    }
}
