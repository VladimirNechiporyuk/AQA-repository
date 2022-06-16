package com.qa.lessons.managers;

import com.qa.lessons.common.Executor;
import com.qa.lessons.data.Group;
import com.qa.lessons.data.Task;

import java.util.UUID;

public interface GroupManager extends Executor {

    Group createGroup(String groupName, UUID groupLeaderId);

    Group changeGroupLeader(UUID groupId, UUID studentId);

    Group addTask(UUID groupId, String taskName);

    Task getTaskById(UUID taskId);

    Task getTaskByName(String taskName);

    void deleteGroup(UUID groupId);

}
