package com.houssem85.toa.tasklist.domain.usecase

import com.houssem85.toa.core.data.Result
import javax.inject.Inject

class DemoRescheduleTaskUseCase @Inject constructor(): RescheduleTaskUseCase {

    override suspend fun invoke(taskId: String): Result<Unit> {
       return Result.Success(Unit)
    }
}