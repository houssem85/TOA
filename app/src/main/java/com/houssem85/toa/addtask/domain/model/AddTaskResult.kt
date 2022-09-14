package com.houssem85.toa.addtask.domain.model

/**
 * A collection of possible results for an attempt to add a new task.
 * */
sealed class AddTaskResult {

    /**
     * A result that indicate that the task is added successfully.
     * */
    object Success : AddTaskResult()

    /**
     * A collection of possible failure results.
     * */
    sealed class Failure : AddTaskResult() {

        object Unknown : Failure()

        data class InvalidInput(
            val emptyDescription: Boolean,
            val scheduledDateInPast: Boolean,
        ) : Failure()
    }
}
