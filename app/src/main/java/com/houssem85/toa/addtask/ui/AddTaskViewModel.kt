package com.houssem85.toa.addtask.ui

import androidx.lifecycle.ViewModel
import com.houssem85.toa.addtask.domain.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow(AddTaskViewState.Initial)
    val viewState: StateFlow<AddTaskViewState> = _viewState.asStateFlow()
}