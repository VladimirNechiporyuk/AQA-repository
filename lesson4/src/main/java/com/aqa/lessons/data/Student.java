package com.aqa.lessons.data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Student {

    private UUID studentCardId; // example A0EEBC99-9C0B-4EF8-BB6D-6BB9BD380A11
    private String name;
    private char gender; // M/F
    private int age;
    private Address address;
    private Map<String, Friend> friends;
    private boolean married;
    private List<String> interests;
    private University university;
    private int courseNumber;

}
