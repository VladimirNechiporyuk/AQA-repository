package com.qa.lessons.managers.impl;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Student;
import com.qa.lessons.managers.StudentManager;
import com.qa.lessons.services.GroupService;
import com.qa.lessons.services.StudentService;
import com.qa.lessons.services.TaskService;

import java.util.List;
import java.util.UUID;

import static com.qa.lessons.enums.Executors.*;

public class StudentManagerImpl implements StudentManager {

    public static StudentManagerImpl INSTANCE;

    private final StudentService studentService = (StudentService) Context.getInstanceFromContext(STUDENT_SERVICE);
    private final TaskService taskService = (TaskService) Context.getInstanceFromContext(TASK_SERVICE);
    private final GroupService groupService = (GroupService) Context.getInstanceFromContext(GROUP_SERVICE);

    public StudentManagerImpl() {
    }

    public static StudentManagerImpl getInstance() {
        StudentManagerImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (StudentManagerImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new StudentManagerImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Student createStudent(String firstName, String lastName) {
        return studentService.createStudent(firstName, lastName);
    }

    @Override
    public Student addStudentToGroup(UUID studentId, UUID groupId) {
        isGroupExists(groupId);
        isStudentExists(studentId);
        groupService.addStudentToGroup(groupId, studentId);
        return studentService.addStudentToGroup(studentId, groupId);
    }

    @Override
    public Student executeTask(UUID studentId, UUID taskId) {
        isStudentExists(studentId);
        isTaskExists(taskId);
        Student student = studentService.executeTask(studentId, taskId);
        taskService.acceptExecution(taskId, studentId);
        return student;
    }

    @Override
    public Student changeStudentName(UUID studentId, String newFirstName, String newLastName) {
        isStudentExists(studentId);
        return studentService.changeStudentName(studentId, newFirstName, newLastName);
    }

    @Override
    public void expelStudent(UUID studentId) {
        isStudentExists(studentId);
        Student student = studentService.getStudent(studentId);
        groupService.expelStudentFromGroup(student.getGroupId(), studentId);
        studentService.expelStudentFromUniversity(studentId);
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

    private void isTaskExists(UUID taskId) {
        if (!taskService.isTaskExists(taskId)) {
            System.out.printf("Task with id '%s' does not exists.%n", taskId);
        }
    }

}
