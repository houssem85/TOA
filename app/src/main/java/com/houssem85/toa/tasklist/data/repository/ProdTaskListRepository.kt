package com.houssem85.toa.tasklist.data.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.data.local.PersistableTask
import com.houssem85.toa.core.data.local.TaskDao
import com.houssem85.toa.core.di.IoDispatcher
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import com.houssem85.toa.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

class ProdTaskListRepository @Inject constructor(
    private val taskDao: TaskDao,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : TaskListRepository {
    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
        return taskDao.getAllTasks().map { persistableTasks ->
            Result.Success(persistableTasks.map(PersistableTask::toTask))
        }.flowOn(coroutineDispatcher)
    }

    override fun fetchTasksForDate(date: LocalDate): Flow<TaskListResult> {
        return taskDao.getTasksForDate(date.toPersistableString())
            .map {
                Result.Success(it.map(PersistableTask::toTask))
            }.flowOn(coroutineDispatcher)
    }

    override suspend fun addTask(task: Task): Result<Unit> = withContext(coroutineDispatcher) {
        taskDao.insertTask(task.toPersistableTask())
        Result.Success(Unit)
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun markAsComplete(task: Task): Result<Unit> {
        TODO("Not yet implemented")
    }
}

private const val PERSISTED_DATE_FORMAT = "yyyy-MM-dd"
private val persistedDateFormatter = DateTimeFormatter.ofPattern(PERSISTED_DATE_FORMAT)

private fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this)
}

private fun LocalDate.toPersistableString(): String {
    return persistedDateFormatter.format(this)
}

fun PersistableTask.toTask(): Task {
    return Task(
        id = this.id,
        description = description,
        scheduledDate = this.scheduledDate.toLocalDate()
    )
}

fun Task.toPersistableTask(): PersistableTask {
    return PersistableTask(
        id = UUID.randomUUID().toString(),
        description = description,
        scheduledDate = this.scheduledDate.toPersistableString()
    )
}
