package com.houssem85.toa.tasklist.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * This class is responsible to managing and exposing the [TaskListViewState] to [TaskListScreen].
 */
@HiltViewModel
class TaskListViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(TaskListViewState.Loading)
    val viewState = _viewState.asStateFlow()
}