package com.houssem85.toa.tasklist.data.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.core.data.local.PersistableTask
import com.houssem85.toa.core.data.local.TaskDao
import com.houssem85.toa.tasklist.domain.model.Task
import com.houssem85.toa.tasklist.domain.repository.TaskListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

class ProdTaskListRepository @Inject constructor(
    private val taskDao: TaskDao,
) : TaskListRepository {
    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
        return taskDao.getAllTasks().map { persistableTasks ->
            Result.Success(persistableTasks.map(PersistableTask::toTask))
        }
    }

    override suspend fun addTask(task: Task): Result<Unit> {
        taskDao.insertTask(task.toPersistableTask())
        return Result.Success(Unit)
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

fun PersistableTask.toTask(): Task {
    return Task(
        id = this.id,
        description = description,
        scheduledDate = LocalDate.parse(this.scheduledDate)
    )
}

fun Task.toPersistableTask(): PersistableTask {
    return PersistableTask(
        id = UUID.randomUUID().toString(),
        description = description,
        scheduledDate = persistedDateFormatter.format(this.scheduledDate)
    )
}
