package com.houssem85.toa.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * A data access object that give access to task table in local database
 * */
@Dao
interface TaskDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertTask(persistableTask: PersistableTask)

    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<PersistableTask>>
}
