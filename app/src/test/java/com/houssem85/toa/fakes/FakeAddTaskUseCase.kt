package com.houssem85.toa.fakes

import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.addtask.domain.usecase.AddTaskUseCase
import com.houssem85.toa.tasklist.domain.model.Task
import io.mockk.coEvery
import io.mockk.mockk

class FakeAddTaskUseCase {

    val mock: AddTaskUseCase = mockk()

    fun mockAddTaskResultForTask(task: Task, addTaskResult: AddTaskResult) {
        coEvery {
            mock.invoke(task)
        } returns addTaskResult
    }
}
