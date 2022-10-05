package com.houssem85.toa.addtask.ui

import com.houssem85.toa.R
import com.houssem85.toa.addtask.domain.model.AddTaskResult
import com.houssem85.toa.addtask.domain.model.TaskInput
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.tasklist.domain.model.Task
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class AddTaskViewModelTest {

    private lateinit var addTaskViewModelRobot: AddTaskViewModelRobot

    @Before
    fun setup() {
        addTaskViewModelRobot = AddTaskViewModelRobot()
    }

    @Test
    fun textSubmitWithEmptyDescription() {
        val testDispatcher = UnconfinedTestDispatcher()
        val description = ""
        val scheduledDate = LocalDate.now()
        val task = Task(
            description = description,
            scheduledDate = scheduledDate,
        )
        addTaskViewModelRobot.buildViewModel(testDispatcher)
        addTaskViewModelRobot.mockAddTaskResultForTask(
            task,
            AddTaskResult.Failure.InvalidInput(
                emptyDescription = true,
                scheduledDateInPast = false
            )
        )
        addTaskViewModelRobot.enterDescription(description)
        addTaskViewModelRobot.selectDate(scheduledDate)
        addTaskViewModelRobot.clickSubmit()
        addTaskViewModelRobot.assertViewState(
            AddTaskViewState.Active(
                TaskInput(
                    description = description,
                    scheduledDate = scheduledDate
                ),
                descriptionInputErrorMessage = UIText.ResourceText(
                    R.string.err_empty_task_description
                ),
                scheduledDateInputErrorMessage = null
            )
        )
    }
}
