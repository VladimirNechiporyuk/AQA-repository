package com.qa.lessons.enums;

public enum Executors {

    DATA_BASE("DataBase"),
    STUDENT_MANAGER("StudentManager"),
    STUDENT_SERVICE("StudentService"),
    GROUP_MANAGER("GroupManager"),
    GROUP_SERVICE("GroupService"),
    TASK_SERVICE("TaskService"),
    VERIFICATION("Verification");

    private String name;

    Executors(String name) {
        this.name = name;
    }

}
