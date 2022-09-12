package com.houssem85.toa.tasklist.di

import com.houssem85.toa.tasklist.domain.usecase.DemoRescheduleTaskUseCase
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
import com.houssem85.toa.tasklist.domain.usecase.ProdGetAllTasksUseCase
import com.houssem85.toa.tasklist.domain.usecase.RescheduleTaskUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module is responsible for defining how to create any use cases
 * */
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetAllTasksUseCase(
        getAllTasksUseCase: ProdGetAllTasksUseCase,
    ): GetAllTasksUseCase

    @Binds
    abstract fun bindRescheduleTaskUseCase(
        rescheduleTaskUseCase: DemoRescheduleTaskUseCase,
    ): RescheduleTaskUseCase
}
