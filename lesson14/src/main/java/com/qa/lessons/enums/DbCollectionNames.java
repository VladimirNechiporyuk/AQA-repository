package com.qa.lessons.enums;

public enum DbCollectionNames {

    STUDENTS_DB_COLLECTION("students"),
    GROUPS_DB_COLLECTION("groups"),
    TASKS_DB_COLLECTION("tasks");

    private String name;

    DbCollectionNames(String name) {
        this.name = name;
    }
}
