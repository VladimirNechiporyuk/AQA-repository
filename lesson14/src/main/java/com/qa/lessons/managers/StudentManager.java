package com.qa.lessons.managers;

import com.qa.lessons.common.Executor;
import com.qa.lessons.data.Student;

import java.util.List;
import java.util.UUID;

public interface StudentManager extends Executor {

    Student createStudent(String firstName, String lastName);

    Student addStudentToGroup(UUID studentId, UUID groupId);

    Student executeTask(UUID studentId, UUID taskId);

    Student changeStudentName(UUID studentId, String newFirstName, String newLastName);

    void expelStudent(UUID studentId); // also need to delete student id from group

}
