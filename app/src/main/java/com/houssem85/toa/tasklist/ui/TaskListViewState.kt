package com.houssem85.toa.tasklist.ui

import com.houssem85.toa.R
import com.houssem85.toa.addtask.ui.TaskDisplayModel
import com.houssem85.toa.core.ui.UIText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TaskListViewState(
    val showLoading: Boolean = true,
    val tasks: List<TaskDisplayModel>? = null,
    val errorMessage: UIText? = null,
    val selectedDate: LocalDate = LocalDate.now(),
) {
    val selectedDateString: UIText
        get() {
            val isToday = (selectedDate == LocalDate.now())
            val isTomorrow = (selectedDate == LocalDate.now().plusDays(1))
            return when {
                isToday -> UIText.ResourceText(R.string.today)
                isTomorrow -> UIText.ResourceText(R.string.today)
                else -> {
                    val uiDateFormat = "MMM dd"
                    val uiString = DateTimeFormatter.ofPattern(uiDateFormat).format(selectedDate)
                    UIText.StringText(uiString)
                }
            }
        }
}
