package com.houssem85.toa.addtask.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.houssem85.toa.addtask.domain.model.TaskInput
import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.core.ui.components.PrimaryButton
import com.houssem85.toa.core.ui.components.TOADatePicker
import com.houssem85.toa.core.ui.components.TOATextField
import com.houssem85.toa.core.ui.components.VerticalSpacer
import com.houssem85.toa.core.ui.getString
import com.houssem85.toa.core.ui.theme.TOATheme
import java.time.LocalDate

@Composable
fun AddTaskContent(
    viewState: AddTaskViewState,
    onDescriptionChanged: (String) -> Unit,
    onScheduledDateChanged: (LocalDate) -> Unit,
    onSubmitClicked: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "what would you to accomplish?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            TOATextField(
                text = viewState.taskInput.description,
                onTextChanged = onDescriptionChanged,
                labelText = "",
                enabled = viewState.inputsEnabled,
                errorMessage = (viewState as? AddTaskViewState.Active)
                    ?.descriptionInputErrorMessage
                    ?.getString(
                        LocalContext.current)
            )
            Text(
                text = "when would you like to do it?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            TOADatePicker(
                value = viewState.taskInput.scheduledDate,
                modifier = Modifier.fillMaxWidth(),
                onDateSelected = onScheduledDateChanged
            )

            if (viewState is AddTaskViewState.SubmissionError) {
                Text(
                    text = viewState.errorMessage.getString(LocalContext.current),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }

            VerticalSpacer(height = 40.dp)

            PrimaryButton(
                text = "Submit",
                onClick = onSubmitClicked,
            )
        }
        if (viewState is AddTaskViewState.Submitting) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AddTaskContentPreview(
    @PreviewParameter(AddTaskViewStateProvider::class)
    addTaskViewState: AddTaskViewState,
) {
    TOATheme {
        Surface {
            AddTaskContent(
                viewState = addTaskViewState,
                onDescriptionChanged = {},
                onScheduledDateChanged = {},
                onSubmitClicked = {}
            )
        }
    }
}

class AddTaskViewStateProvider : PreviewParameterProvider<AddTaskViewState> {
    override val values: Sequence<AddTaskViewState>
        get() {
            return sequenceOf(
                AddTaskViewState.Initial,
                AddTaskViewState.Active(
                    TaskInput(
                        description = "desription",
                        scheduledDate = LocalDate.now()
                    )
                ),
                AddTaskViewState.Submitting(
                    TaskInput(
                        description = "desription",
                        scheduledDate = LocalDate.now()
                    )
                ),
                AddTaskViewState.SubmissionError(
                    TaskInput(
                        description = "desription",
                        scheduledDate = LocalDate.now(),
                    ),
                    errorMessage = UIText.StringText("error")
                ),
                AddTaskViewState.Completed,
            )
        }
}
