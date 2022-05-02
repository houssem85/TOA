package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.CoroutinesTestRule
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskListViewModelTest {
    private val testRobot = TaskListViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun testActiveState() {
        val tasks = (1..20).map {
            Task(
                description = "task $it"
            )
        }
        testRobot.mockResult(Result.Success(tasks))
        testRobot.buildViewModel()
        testRobot.assertViewState(TaskListViewState.Active(tasks))
    }

    @Test
    fun testErrorState() {
        testRobot.mockResult(Result.Error(Throwable("Something went wrong.")))
        testRobot.buildViewModel()
        testRobot.assertViewState(TaskListViewState.Error(UIText.StringText("Something went wrong.")))
    }
}
