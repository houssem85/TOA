package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result

interface RescheduleTaskUseCase {

    suspend operator fun invoke(taskId: String): Result<Unit>
}
