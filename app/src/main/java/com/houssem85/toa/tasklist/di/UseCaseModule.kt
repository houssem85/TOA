package com.houssem85.toa.tasklist.di

import com.houssem85.toa.tasklist.domain.usecase.DemoGetAllTasksUseCase
import com.houssem85.toa.tasklist.domain.usecase.GetAllTasksUseCase
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
        getAllTasksUseCase: DemoGetAllTasksUseCase,
    ): GetAllTasksUseCase
}
