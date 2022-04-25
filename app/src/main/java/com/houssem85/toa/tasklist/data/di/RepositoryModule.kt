package com.houssem85.toa.tasklist.data.di

import com.houssem85.toa.tasklist.data.repository.DemoTaskListRepository
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskListRepository(
        taskListRepository: DemoTaskListRepository,
    ): TaskListRepository
}
