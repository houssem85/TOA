package com.houssem85.toa.addtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.R
import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.addtask.domain.usecase.AddTaskUseCase
import com.houssem85.toa.core.di.IoDispatcher
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _viewState = MutableStateFlow<AddTaskViewState>(AddTaskViewState.Initial)
    val viewState: StateFlow<AddTaskViewState> = _viewState.asStateFlow()

    fun onTaskScheduledDateChanged(newDate: LocalDate) {
        val currentInput = _viewState.value.taskInput
        val newInput = currentInput.copy(
            scheduledDate = newDate
        )
        _viewState.value = AddTaskViewState.Active(
            taskInput = newInput,
        )
    }

    fun onTaskDescriptionChanged(newDescription: String) {
        val currentInput = _viewState.value.taskInput
        val newInput = currentInput.copy(
            description = newDescription
        )
        _viewState.value = AddTaskViewState.Active(
            taskInput = newInput,
        )
    }

    fun onSubmitButtonClicked() {
        val task = Task(
            description = _viewState.value.taskInput.description,
            scheduledDate = _viewState.value.taskInput.scheduledDate
        )
        viewModelScope.launch(context = dispatcher) {
            val result = addTaskUseCase(task)
            _viewState.value = when (result) {
                is AddTaskResult.Failure.InvalidInput -> {
                    AddTaskViewState.Active(
                        taskInput = _viewState.value.taskInput,
                        descriptionInputErrorMessage = UIText.ResourceText(R.string.err_empty_task_description)
                            .takeIf {
                                result.emptyDescription
                            },
                        scheduledDateInputErrorMessage = UIText.ResourceText(R.string.err_invalid_scheduled_date)
                            .takeIf {
                                result.scheduledDateInPast
                            },
                    )
                }
                is AddTaskResult.Failure.Unknown -> {
                    AddTaskViewState.SubmissionError(
                        _viewState.value.taskInput,
                        UIText.StringText(
                            value = "Unable to add task."
                        )
                    )
                }
                AddTaskResult.Success -> {
                    AddTaskViewState.Completed
                }
            }
        }
    }
}
