package com.qa.lessons.services;

import com.qa.lessons.common.Executor;
import com.qa.lessons.data.Group;

import java.util.UUID;

public interface GroupService extends Executor {

    Group createGroup(String groupName, UUID groupLeaderId);

    Group addStudentToGroup(UUID groupId, UUID studentId);

    Group getGroup(UUID groupId);

    Group changeGroupLeader(UUID groupId, UUID studentId);

    Group addTask(UUID groupId, UUID taskId);

    boolean isGroupExists(UUID groupId);

    void expelStudentFromGroup(UUID groupId, UUID studentId);

    void deleteGroup(UUID groupId);

}
