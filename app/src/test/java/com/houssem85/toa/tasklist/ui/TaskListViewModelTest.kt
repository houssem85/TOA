package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    private val testRobot = TaskListViewModelRobot()

    private val standardTestDispatcher: TestDispatcher = StandardTestDispatcher()

    private val unconfinedTestDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Test
    fun testActiveState() {
        val tasks = (1..20).map {
            Task(
                description = "task $it"
            )
        }

        testRobot.mockResult(Result.Success(tasks))
        testRobot.buildViewModel(standardTestDispatcher)
        testRobot.assertViewState(TaskListViewState.Loading)
        standardTestDispatcher.scheduler.runCurrent()
        testRobot.assertViewState(TaskListViewState.Active(tasks))
    }

    @Test
    fun testErrorState() {
        testRobot.mockResult(Result.Error(Throwable("Something went wrong.")))
        testRobot.buildViewModel(unconfinedTestDispatcher)
        testRobot.assertViewState(TaskListViewState.Error(UIText.StringText("Something went wrong.")))
    }
}
