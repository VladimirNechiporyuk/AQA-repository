package com.qa.lessons.managers.impl;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Group;
import com.qa.lessons.data.Student;
import com.qa.lessons.data.Task;
import com.qa.lessons.managers.GroupManager;
import com.qa.lessons.services.GroupService;
import com.qa.lessons.services.StudentService;
import com.qa.lessons.services.TaskService;

import java.util.List;
import java.util.UUID;

import static com.qa.lessons.enums.Executors.*;

public class GroupManagerImpl implements GroupManager {

    private static GroupManagerImpl INSTANCE;

    private final GroupService groupService = (GroupService) Context.getInstanceFromContext(GROUP_SERVICE);
    private final StudentService studentService = (StudentService) Context.getInstanceFromContext(STUDENT_SERVICE);
    private final TaskService taskService = (TaskService) Context.getInstanceFromContext(TASK_SERVICE);

    public GroupManagerImpl() {
    }

    public static GroupManagerImpl getInstance() {
        GroupManagerImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (GroupManagerImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new GroupManagerImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Group createGroup(String groupName, UUID groupLeaderId) {
        isStudentExists(groupLeaderId);
        Group group = groupService.createGroup(groupName, groupLeaderId);
        studentService.addStudentToGroup(groupLeaderId, group.getId());
        return group;
    }

    @Override
    public Group changeGroupLeader(UUID groupId, UUID newGroupLeaderId) {
        isStudentExists(newGroupLeaderId);
        Group group = groupService.changeGroupLeader(groupId, newGroupLeaderId);
        studentService.addStudentToGroup(newGroupLeaderId, group.getId());
        return group;
    }

    @Override
    public Group addTask(UUID groupId, String taskName) {
        isGroupExists(groupId);
        Task task = taskService.createTask(groupId, taskName);
        return groupService.addTask(groupId, task.getId());
    }

    @Override
    public Task getTaskById(UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @Override
    public Task getTaskByName(String taskName) {
        return taskService.getTaskByName(taskName);
    }

    @Override
    public void deleteGroup(UUID groupId) {
        List<Student> students = studentService.getAllStudentsByGroup(groupId);
        if (!students.isEmpty()) {
            students.forEach(student -> student.setGroupId(null));
        }
        groupService.deleteGroup(groupId);
    }

    private void isStudentExists(UUID studentId) {
        if (!studentService.isStudentExists(studentId)) {
            throw new RuntimeException(String.format("Student with id '%s' does not exists.", studentId));
        }
    }

    private void isGroupExists(UUID groupId) {
        if (!groupService.isGroupExists(groupId)) {
            System.out.printf("Group with id '%s' does not exists.%n", groupId);
        }
    }

}
