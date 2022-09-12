package com.houssem85.toa.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A room entity class that represent the task table
 * @property [id] a unique uuid , it is a primary key
 * @property [description] description that represent the meaning of a task
 * @property [scheduledDate] date task execution
 * */
@Entity(tableName = "task")
data class PersistableTask(
    @PrimaryKey
    val id: String,
    val description: String,
    val scheduledDate: String,
)
