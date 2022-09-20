package com.houssem85.toa.addtask.ui

import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.fakes.FakeAddTaskUseCase
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate

class AddTaskViewModelRobot {

    private val addTaskUseCase = FakeAddTaskUseCase()
    private lateinit var viewModel: AddTaskViewModel

    fun buildViewModel(coroutineDispatcher: CoroutineDispatcher) {
        viewModel = AddTaskViewModel(addTaskUseCase.mock, coroutineDispatcher)
    }

    fun mockAddTaskResultForTask(task: Task, addTaskResult: AddTaskResult) {
        addTaskUseCase.mockAddTaskResultForTask(task, addTaskResult)
    }

    fun enterDescription(description: String) {
        viewModel.onTaskDescriptionChanged(description)
    }

    fun selectDate(date: LocalDate) {
        viewModel.onTaskScheduledDateChanged(date)
    }

    fun clickSubmit() {
        viewModel.onSubmitButtonClicked()
    }

    fun assertViewState(
        expectedAddTaskViewState: AddTaskViewState,
    ) {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedAddTaskViewState)
    }
}
