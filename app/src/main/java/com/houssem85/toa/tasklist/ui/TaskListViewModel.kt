package com.houssem85.toa.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.addtask.ui.TaskDisplayModel
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.di.IoDispatcher
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
import com.houssem85.toa.tasklist.domain.usecase.RescheduleTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * This class is responsible to managing and exposing the [TaskListViewState] to [TaskListScreen].
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val rescheduleTaskUseCase: RescheduleTaskUseCase,
    @IoDispatcher private val defaultDispatchers: CoroutineDispatcher,
) : ViewModel() {
    private val _viewState = MutableStateFlow<TaskListViewState>(TaskListViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatchers) {
            getAllTasksUseCase().collect { result ->
                _viewState.value = when (result) {
                    is Result.Success -> {
                        TaskListViewState.Active(
                            result.data.map {
                                it.toTaskDisplayModel(
                                    onTaskClicked = {
                                        viewModelScope.launch(defaultDispatchers) {
                                            rescheduleTaskUseCase(it.id)
                                        }
                                    }, onDoneClicked = {
                                }
                                )
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
}

fun Task.toTaskDisplayModel(
    onTaskClicked: () -> Unit = {},
    onDoneClicked: () -> Unit = {},
): TaskDisplayModel {
    val friendlyDatePattern = "MMM dd, yyyy"
    val friendlyDateFormatter =
        DateTimeFormatter.ofPattern(friendlyDatePattern)
    return TaskDisplayModel(
        description = this.description,
        scheduledDate = friendlyDateFormatter.format(this.scheduledDate),
        onTaskClicked = onTaskClicked,
        onDoneClicked = onDoneClicked
    )
}
