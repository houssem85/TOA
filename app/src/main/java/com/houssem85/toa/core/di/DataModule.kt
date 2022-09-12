package com.houssem85.toa.core.di

import android.content.Context
import androidx.room.Room
import com.houssem85.toa.core.data.local.TOADatabase
import com.houssem85.toa.core.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideTOADatabase(@ApplicationContext context: Context): TOADatabase {
        return Room.databaseBuilder(
            context,
            TOADatabase::class.java, "toa-database"
        ).build()
    }

    @Provides
    fun provideTaskDao(toaDatabase: TOADatabase): TaskDao {
        return toaDatabase.taskDao()
    }
}
