package com.houssem85.toa.addtask.domain.di

import com.houssem85.toa.addtask.domain.usecase.AddTaskUseCase
import com.houssem85.toa.addtask.domain.usecase.ProdAddTaskUseCase
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
    abstract fun bindAddTaskUseCase(
        addTaskUseCase: ProdAddTaskUseCase,
    ): AddTaskUseCase
}
