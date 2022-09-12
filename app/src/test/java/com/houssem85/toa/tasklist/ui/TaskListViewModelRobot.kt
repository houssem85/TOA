package com.houssem85.toa.tasklist.ui

import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.fakes.FakeGetAllTasksUseCase
import com.houssem85.toa.fakes.FakeRescheduleTaskUseCase
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class TaskListViewModelRobot {

    private lateinit var viewModel: TaskListViewModel
    private val fakeGetAllTasksUseCase = FakeGetAllTasksUseCase()
    private val fakeRescheduleTaskUseCase = FakeRescheduleTaskUseCase()

    fun buildViewModel(dispatcher: CoroutineDispatcher) {
        viewModel = TaskListViewModel(
            getAllTasksUseCase = fakeGetAllTasksUseCase.mock,
            rescheduleTaskUseCase = fakeRescheduleTaskUseCase.mock,
            defaultDispatchers = dispatcher
        )
    }

    fun mockResult(result: Flow<Result<List<Task>>>) {
        fakeGetAllTasksUseCase.mockResult(result)
    }

    fun mockRescheduleTaskResult(taskId: String, result: Result<Unit>) {
        fakeRescheduleTaskUseCase.mockRescheduleTaskResult(taskId, result)
    }

    fun assertViewState(expectedViewState: TaskListViewState) {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
