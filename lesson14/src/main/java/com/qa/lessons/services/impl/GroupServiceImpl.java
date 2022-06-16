package com.qa.lessons.services.impl;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Group;
import com.qa.lessons.database.DataBase;
import com.qa.lessons.services.GroupService;

import java.util.UUID;

import static com.qa.lessons.enums.DbCollectionNames.GROUPS_DB_COLLECTION;
import static com.qa.lessons.enums.Executors.DATA_BASE;

public class GroupServiceImpl implements GroupService {

    public static GroupServiceImpl INSTANCE;

    private final DataBase dataBase = (DataBase) Context.getInstanceFromContext(DATA_BASE);

    public GroupServiceImpl() {
    }

    public static GroupServiceImpl getInstance() {
        GroupServiceImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (GroupServiceImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new GroupServiceImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Group createGroup(String groupName, UUID groupLeaderId) {
        Group group = new Group(UUID.randomUUID(), groupLeaderId);
        return (Group) dataBase.saveNewEntity(GROUPS_DB_COLLECTION, group.getId(), group);
    }

    @Override
    public Group addStudentToGroup(UUID groupId, UUID studentId) {
        Group group = getGroup(groupId);
        group.getStudents().add(studentId);
        return (Group) dataBase.updateEntity(GROUPS_DB_COLLECTION, groupId, group);
    }

    @Override
    public Group getGroup(UUID groupId) {
        return (Group) dataBase.getEntity(GROUPS_DB_COLLECTION, groupId);
    }

    @Override
    public Group changeGroupLeader(UUID groupId, UUID studentId) {
        Group group = getGroup(groupId);
        verifyIsGroupIncludesStudent(group, studentId);
        group.setGroupLeaderId(studentId);
        return (Group) dataBase.updateEntity(GROUPS_DB_COLLECTION, groupId, group);
    }

    @Override
    public Group addTask(UUID groupId, UUID taskId) {
        Group group = getGroup(groupId);
        group.getTasks().add(taskId);
        return (Group) dataBase.updateEntity(GROUPS_DB_COLLECTION, groupId, group);
    }

    @Override
    public boolean isGroupExists(UUID groupId) {
        return dataBase.isEntityExists(GROUPS_DB_COLLECTION, groupId);
    }

    @Override
    public void expelStudentFromGroup(UUID groupId, UUID studentId) {
        Group group = getGroup(groupId);
        if (group.getGroupLeaderId().equals(studentId)) {
            changeGroupLeader(groupId, group.getStudents().get(0));
        }
        group.getStudents().remove(studentId);
        dataBase.updateEntity(GROUPS_DB_COLLECTION, groupId, group);
    }

    @Override
    public void deleteGroup(UUID groupId) {
        if (dataBase.isEntityExists(GROUPS_DB_COLLECTION, groupId)) {
            dataBase.deleteEntity(GROUPS_DB_COLLECTION, groupId);
        }
    }

    private void verifyIsGroupIncludesStudent(Group group, UUID studentId) {
        if (!group.getStudents().contains(studentId)) {
            System.out.printf("Group with id '%s' does not contains student '%s'%n", group.getId(), studentId);
        }
    }

}
