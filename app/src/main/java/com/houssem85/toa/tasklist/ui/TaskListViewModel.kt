package com.houssem85.toa.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.addtask.ui.toTaskDisplayModel
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.di.IoDispatcher
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class is responsible to managing and exposing the [TaskListViewState] to [TaskListScreen].
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    @IoDispatcher private val defaultDispatchers: CoroutineDispatcher,
) : ViewModel() {
    private val _viewState = MutableStateFlow<TaskListViewState>(TaskListViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatchers) {
            val result = getAllTasksUseCase()
            _viewState.value = when (result) {
                is Result.Success -> {
                    TaskListViewState.Active(
                        result.data.map {
                            it.toTaskDisplayModel()
                        }
                    )
                }
                is Result.Error -> {
                    TaskListViewState.Error(UIText.StringText("Something went wrong."))
                }
            }
        }
    }
}
