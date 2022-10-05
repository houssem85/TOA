package com.houssem85.toa.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssem85.toa.addtask.ui.TaskDisplayModel
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.di.IoDispatcher
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.usecase.GetTasksForDateUseCase
import com.houssem85.toa.tasklist.domain.usecase.RescheduleTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * This class is responsible to managing and exposing the [TaskListViewState] to [TaskListScreen].
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksForDateUseCase: GetTasksForDateUseCase,
    private val rescheduleTaskUseCase: RescheduleTaskUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _viewState = MutableStateFlow(TaskListViewState())
    val viewState = _viewState.asStateFlow()

    init {
        _viewState.map {
            it.selectedDate
        }.distinctUntilChanged()
            .onEach { date ->
                _viewState.value = _viewState.value.copy(showLoading = true)
            }.flatMapConcat { date ->
                getTasksForDateUseCase(date)
            }.onEach { result ->
                _viewState.value = when (result) {
                    is Result.Success -> {
                        val tasks = result.data.map {
                            it.toTaskDisplayModel(
                                onTaskClicked = {
                                    viewModelScope.launch {
                                        rescheduleTaskUseCase(it.id)
                                    }
                                }, onDoneClicked = {
                                }
                            )
                        }
                        _viewState.value.copy(
                            showLoading = false,
                            tasks = tasks,
                            errorMessage = null
                        )
                    }
                    is Result.Error -> {
                        _viewState.value.copy(
                            showLoading = false,
                            errorMessage = UIText.StringText("Something went wrong.")
                        )
                    }
                }
            }
            .flowOn(coroutineDispatcher)
            .launchIn(viewModelScope)
    }

    fun onNextDateClicked() {
        _viewState.value = _viewState.value.copy(
            selectedDate = _viewState.value.selectedDate.plusDays(1)
        )
    }

    fun onPreviousDateClicked() {
        _viewState.value = _viewState.value.copy(
            selectedDate = _viewState.value.selectedDate.minusDays(1)
        )
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
