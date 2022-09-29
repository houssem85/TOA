package com.houssem85.toa.addtask.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.houssem85.toa.addtask.domain.model.TaskInput
import com.houssem85.toa.core.ui.UIText
import org.junit.Rule
import org.junit.Test

class AddTaskContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderInvalidInputMessages() {
        val descriptionError = "description error"
        val scheduledDateError = "scheduled date error"

        val viewState = AddTaskViewState.Active(
            taskInput = TaskInput(),
            descriptionInputErrorMessage = UIText.StringText(descriptionError),
            scheduledDateInputErrorMessage = UIText.StringText(scheduledDateError)
        )
        composeTestRule.setContent {
            AddTaskContent(
                viewState = viewState,
                onDescriptionChanged = {},
                onScheduledDateChanged = {},
                onSubmitClicked = {}
            )
        }

        composeTestRule.onNodeWithText(descriptionError).assertIsDisplayed()
        composeTestRule.onNodeWithText(scheduledDateError).assertIsDisplayed()
    }
}
