package com.qa.lessons;

import com.qa.lessons.common.Context;
import com.qa.lessons.data.Group;
import com.qa.lessons.data.Student;
import com.qa.lessons.data.Task;
import com.qa.lessons.database.DataBase;
import com.qa.lessons.enums.Executors;
import com.qa.lessons.managers.GroupManager;
import com.qa.lessons.managers.StudentManager;
import com.qa.lessons.services.GroupService;
import com.qa.lessons.services.StudentService;
import com.qa.lessons.services.TaskService;

import static com.qa.lessons.enums.Executors.*;

public class Main {

//    GroupManager createGroup +
//    GroupManager changeGroupLeader +
//    GroupManager addTask +
//    GroupManager deleteGroup +

//    StudentManager createStudent +
//    StudentManager addStudentToGroup +
//    StudentManager executeTask +
//    StudentManager changeStudentName +
//    StudentManager expelStudent +

    private static DataBase dataBase;
    private static TaskService taskService;
    private static StudentService studentService;
    private static GroupService groupService;
    private static StudentManager studentManager;
    private static GroupManager groupManager;

    public static void main(String[] args) {
        Context.provideExecutors();
        dataBase = (DataBase) Context.getInstanceFromContext(Executors.DATA_BASE);
        studentService = (StudentService) Context.getInstanceFromContext(STUDENT_SERVICE);
        groupService = (GroupService) Context.getInstanceFromContext(GROUP_SERVICE);
        taskService = (TaskService) Context.getInstanceFromContext(TASK_SERVICE);
        studentManager = (StudentManager) Context.getInstanceFromContext(Executors.STUDENT_MANAGER);
        groupManager = (GroupManager) Context.getInstanceFromContext(Executors.GROUP_MANAGER);

        Student volodymyrNechiporyuk = studentManager.createStudent("Volodymyr", "Nechiporyuk");
        Student bondarSvitlana = studentManager.createStudent("Bondar", "Svitlana");
        Student tsvirkunBohdan = studentManager.createStudent("Tsvirkun", "Bohdan");
        Group rs21 = groupManager.createGroup("RS21", bondarSvitlana.getId());
        studentManager.addStudentToGroup(volodymyrNechiporyuk.getId(), rs21.getId());
        groupManager.changeGroupLeader(rs21.getId(), tsvirkunBohdan.getId());
        groupManager.addTask(rs21.getId(), "Learn English");
        Task task = groupManager.getTaskByName("Learn English");
        studentManager.executeTask(volodymyrNechiporyuk.getId(), task.getId());
        studentManager.changeStudentName(bondarSvitlana.getId(), "Mariу", "Сurie");
        studentManager.expelStudent(tsvirkunBohdan.getId());
        groupManager.deleteGroup(rs21.getId());

        System.out.println(dataBase.getData());
    }

}
