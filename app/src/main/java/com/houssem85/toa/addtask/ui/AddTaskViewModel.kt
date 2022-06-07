package com.houssem85.toa.addtask.ui

import androidx.lifecycle.ViewModel
import com.houssem85.toa.addtask.domain.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<AddTaskViewState>(AddTaskViewState.Initial)
    val viewState: StateFlow<AddTaskViewState> = _viewState.asStateFlow()

    fun onTaskScheduledDateChanged(newDate: LocalDate) {
        val currentInput = _viewState.value.taskInput
        val newInput = currentInput.copy(
            scheduledDate = newDate
        )
        _viewState.value = AddTaskViewState.Active(newInput)
    }

    fun onTaskDescriptionChanged(newDescription: String) {
        val currentInput = _viewState.value.taskInput
        val newInput = currentInput.copy(
            description = newDescription
        )
        _viewState.value = AddTaskViewState.Active(newInput)
    }
}
