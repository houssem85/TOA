package com.houssem85.toa.tasklist.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.fakes.FakeGetTasksForDateUseCase
import com.houssem85.toa.fakes.FakeRescheduleTaskUseCase
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TaskListViewModelRobot {

    private lateinit var viewModel: TaskListViewModel
    private val fakeGetTasksForDateUseCase = FakeGetTasksForDateUseCase()
    private val fakeRescheduleTaskUseCase = FakeRescheduleTaskUseCase()

    fun buildViewModel(coroutineDispatcher: CoroutineDispatcher) {
        viewModel = TaskListViewModel(
            getTasksForDateUseCase = fakeGetTasksForDateUseCase.mock,
            rescheduleTaskUseCase = fakeRescheduleTaskUseCase.mock,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    fun mockResult(date: LocalDate, result: Flow<Result<List<Task>>>) {
        fakeGetTasksForDateUseCase.mockResult(date, result)
    }

    fun mockRescheduleTaskResult(taskId: String, result: Result<Unit>) {
        fakeRescheduleTaskUseCase.mockRescheduleTaskResult(taskId, result)
    }

    fun clickNextButton() {
        viewModel.onNextDateClicked()
    }

    fun assertViewState(expectedViewState: TaskListViewState) {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    suspend fun assertViewStatesAfterAction(
        action: TaskListViewModelRobot.() -> Unit,
        expectedViewStates: List<TaskListViewState>,
    ) {
        viewModel.viewState.test {
            action()
            for (state in expectedViewStates) {
                val awaitItem = awaitItem()
                assertThat(awaitItem).isEqualTo(state)
            }
            this.cancel()
        }
    }
}
