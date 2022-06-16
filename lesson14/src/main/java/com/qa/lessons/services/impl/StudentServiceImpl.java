package com.qa.lessons.services.impl;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Student;
import com.qa.lessons.database.DataBase;
import com.qa.lessons.services.StudentService;

import java.util.List;
import java.util.UUID;

import static com.qa.lessons.enums.DbCollectionNames.STUDENTS_DB_COLLECTION;
import static com.qa.lessons.enums.Executors.DATA_BASE;

public class StudentServiceImpl implements StudentService {

    public static StudentServiceImpl INSTANCE;

    private final DataBase dataBase = (DataBase) Context.getInstanceFromContext(DATA_BASE);

    public StudentServiceImpl() {
    }

    public static StudentServiceImpl getInstance() {
        StudentServiceImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (StudentServiceImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new StudentServiceImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Student createStudent(String firstName, String lastName) {
        Student student = new Student(UUID.randomUUID(), firstName, lastName);
        return (Student) dataBase.saveNewEntity(STUDENTS_DB_COLLECTION, student.getId(), student);
    }

    @Override
    public Student addStudentToGroup(UUID studentId, UUID groupId) {
        Student student = getStudent(studentId);
        student.setGroupId(groupId);
        return (Student) dataBase.updateEntity(STUDENTS_DB_COLLECTION, studentId, student);
    }

    @Override
    public Student getStudent(UUID studentId) {
        return (Student) dataBase.getEntity(STUDENTS_DB_COLLECTION, studentId);
    }

    @Override
    public List<Student> getAllStudentsByGroup(UUID groupId) {
        return dataBase.getAllEntities(STUDENTS_DB_COLLECTION)
                .stream()
                .map(o -> (Student) o)
                .filter(student -> student.getGroupId() != null && student.getGroupId().equals(groupId))
                .toList();
    }

    @Override
    public Student executeTask(UUID studentId, UUID taskId) {
        Student student = getStudent(studentId);
        student.getExecutedTasks().add(taskId);
        return (Student) dataBase.updateEntity(STUDENTS_DB_COLLECTION, studentId, student);
    }

    @Override
    public Student changeStudentName(UUID studentId, String newFirstName, String newLastName) {
        Student student = getStudent(studentId);
        student.setFirstName(newFirstName);
        student.setLastName(newLastName);
        return (Student) dataBase.updateEntity(STUDENTS_DB_COLLECTION, studentId, student);
    }

    @Override
    public boolean isStudentExists(UUID studentId) {
        return dataBase.isEntityExists(STUDENTS_DB_COLLECTION, studentId);
    }

    @Override
    public void expelStudentFromUniversity(UUID studentId) {
        dataBase.deleteEntity(STUDENTS_DB_COLLECTION, studentId);
    }
}
