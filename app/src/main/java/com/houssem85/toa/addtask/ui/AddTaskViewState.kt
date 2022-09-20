package com.houssem85.toa.addtask.ui

import com.houssem85.toa.addtask.domain.model.TaskInput
import com.houssem85.toa.core.ui.UIText

sealed class AddTaskViewState(
    open val taskInput: TaskInput,
    val inputsEnabled: Boolean = true,
) {

    object Initial : AddTaskViewState(
        taskInput = TaskInput(),
        inputsEnabled = true,
    )

    data class Active(
        override val taskInput: TaskInput,
        val descriptionInputErrorMessage: UIText? = null,
        val scheduledDateInputErrorMessage: UIText? = null,
    ) : AddTaskViewState(
        taskInput = taskInput,
        inputsEnabled = true,
    )

    data class Submitting(
        override val taskInput: TaskInput,
    ) : AddTaskViewState(
        taskInput = taskInput,
        inputsEnabled = false,
    )

    data class SubmissionError(
        override val taskInput: TaskInput,
        val errorMessage: UIText,
    ) : AddTaskViewState(
        taskInput = taskInput,
        inputsEnabled = true,
    )

    object Completed : AddTaskViewState(
        taskInput = TaskInput(),
        inputsEnabled = false,
    )
}
