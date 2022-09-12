package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    private val testRobot = TaskListViewModelRobot()

    private val standardTestDispatcher: TestDispatcher = StandardTestDispatcher()

    private val unconfinedTestDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Test
    fun testActiveState() {
        val tasks = (1..1).map {
            Task(
                description = "task $it",
                scheduledDate = LocalDate.now()
            )
        }

        testRobot.mockResult(flowOf(Result.Success(tasks)))
        testRobot.buildViewModel(standardTestDispatcher)
        testRobot.assertViewState(TaskListViewState.Loading)
        standardTestDispatcher.scheduler.runCurrent()
        testRobot.assertViewState(TaskListViewState.Active(tasks.map { it.toTaskDisplayModel() }))
    }

    @Test
    fun testErrorState() {
        testRobot.mockResult(flowOf(Result.Error(Throwable("Something went wrong."))))
        testRobot.buildViewModel(unconfinedTestDispatcher)
        testRobot.assertViewState(TaskListViewState.Error(UIText.StringText("Something went wrong.")))
    }
}
