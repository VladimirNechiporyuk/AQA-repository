package com.qa.lessons.services.impl;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Task;
import com.qa.lessons.database.DataBase;
import com.qa.lessons.services.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.qa.lessons.enums.DbCollectionNames.TASKS_DB_COLLECTION;
import static com.qa.lessons.enums.Executors.DATA_BASE;

public class TaskServiceImpl implements TaskService {

    private static TaskServiceImpl INSTANCE;
    private final DataBase dataBase = (DataBase) Context.getInstanceFromContext(DATA_BASE);

    public TaskServiceImpl() {
    }

    public static TaskServiceImpl getInstance() {
        TaskServiceImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (TaskServiceImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new TaskServiceImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Task createTask(UUID groupId, String name) {
        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, name, groupId);
        return (Task) dataBase.saveNewEntity(TASKS_DB_COLLECTION, taskId, task);
    }

    @Override
    public Task getTaskById(UUID taskId) {
        return (Task) dataBase.getEntity(TASKS_DB_COLLECTION, taskId);
    }

    @Override
    public Task getTaskByName(String taskName) {
        Optional<Task> optionalTask = dataBase.getAllEntities(TASKS_DB_COLLECTION)
                .stream()
                .map(o -> (Task) o)
                .filter(task -> task.getName().equals(taskName))
                .findFirst();
        return optionalTask.orElse(null);
    }

    @Override
    public List<Task> getAllTasksForGroup(UUID groupId) {
        return dataBase.getAllEntities(TASKS_DB_COLLECTION)
                .stream()
                .map(o -> (Task) o)
                .filter(task -> task.getGroupId().equals(groupId))
                .collect(Collectors.toList());
    }

    @Override
    public void acceptExecution(UUID taskId, UUID studentId) {
        Task task = (Task) dataBase.getEntity(TASKS_DB_COLLECTION, taskId);
        task.getTaskFinishedStudentIds().add(studentId);
        dataBase.updateEntity(TASKS_DB_COLLECTION, taskId, task);
    }

    @Override
    public boolean isTaskExists(UUID taskId) {
        return dataBase.isEntityExists(TASKS_DB_COLLECTION, taskId);
    }
}
