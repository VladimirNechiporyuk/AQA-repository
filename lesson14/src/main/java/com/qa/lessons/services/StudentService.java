package com.qa.lessons.services;

import com.qa.lessons.common.Executor;
import com.qa.lessons.data.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService extends Executor {

    Student createStudent(String firstName, String lastName);

    Student addStudentToGroup(UUID studentId, UUID groupId);

    Student getStudent(UUID studentId);

    List<Student> getAllStudentsByGroup(UUID groupId);

    Student executeTask(UUID studentId, UUID taskId);

    Student changeStudentName(UUID studentId, String newFirstName, String newLastName);

    boolean isStudentExists(UUID studentId);

    void expelStudentFromUniversity(UUID studentId); // also need to delete student id from group

}
