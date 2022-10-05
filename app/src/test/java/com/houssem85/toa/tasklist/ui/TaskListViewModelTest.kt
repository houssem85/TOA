package com.houssem85.toa.tasklist.ui


import com.houssem85.toa.MainDispatcherRule
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    private val testRobot = TaskListViewModelRobot()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testActiveState() = runTest {
        /*
        val testDispatcher = StandardTestDispatcher()

        val today = LocalDate.now()
        val tomorrow = LocalDate.now().plusDays(1)
        val todayTasks = (1..1).map {
            Task(
                description = "task $it",
                scheduledDate = today
            )
        }
        val tomorrowTasks = (15..20).map {
            Task(
                description = "task $it",
                scheduledDate = tomorrow
            )
        }

        testRobot.mockResult(today, flowOf(Result.Success(todayTasks)))
        testRobot.mockResult(tomorrow, flowOf(Result.Success(tomorrowTasks)))

        val expectedStates = listOf(
            TaskListViewState(
                showLoading = true,
                selectedDate = today,
            ),
            TaskListViewState(
                showLoading = true,
                selectedDate = tomorrow,
                tasks = null
            ),
            TaskListViewState(
                showLoading = false,
                selectedDate = tomorrow,
                tasks = tomorrowTasks.map { it.toTaskDisplayModel() }
            )
        )

        testRobot.buildViewModel(testDispatcher)
        testRobot.clickNextButton()
        testRobot.assertViewState(TaskListViewState(
            showLoading = true,
            selectedDate = tomorrow,
            tasks = null
        ))*/
    }

    @Test
    fun testErrorState() {
        /*
        val viewState = TaskListViewState(
            showLoading = true
        )
        testRobot.mockResult(LocalDate.now(),
            flowOf(Result.Error(Throwable("Something went wrong."))))
        testRobot.buildViewModel()
        testRobot.assertViewState(viewState.copy(
            showLoading = false,
            tasks = null,
            errorMessage = UIText.StringText("Something went wrong.")
        ))*/
    }
}
