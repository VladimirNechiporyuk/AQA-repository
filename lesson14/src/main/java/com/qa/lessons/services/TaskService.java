package com.qa.lessons.services;

import com.qa.lessons.common.Executor;
import com.qa.lessons.data.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService extends Executor {

    Task createTask(UUID groupId, String name);

    Task getTaskById(UUID taskId);

    Task getTaskByName(String taskName);

    List<Task> getAllTasksForGroup(UUID groupId);

    void acceptExecution(UUID taskId, UUID studentId);

    boolean isTaskExists(UUID taskId);
}
