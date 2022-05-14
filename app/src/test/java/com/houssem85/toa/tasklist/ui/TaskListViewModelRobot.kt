package com.houssem85.toa.tasklist.ui

import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.fakes.FakeGetAllTasksUseCase
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.CoroutineDispatcher

class TaskListViewModelRobot {

    private lateinit var viewModel: TaskListViewModel
    private val fakeGetAllTasksUseCase = FakeGetAllTasksUseCase()

    fun buildViewModel(dispatcher: CoroutineDispatcher) {
        viewModel = TaskListViewModel(
            getAllTasksUseCase = fakeGetAllTasksUseCase.mock,
            defaultDispatchers = dispatcher
        )
    }

    fun mockResult(result: Result<List<Task>>) {
        fakeGetAllTasksUseCase.mockResult(result)
    }

    fun assertViewState(expectedViewState: TaskListViewState) {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
