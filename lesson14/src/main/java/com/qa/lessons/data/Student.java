package com.qa.lessons.data;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends ObjectWithData {

    private String firstName;
    private String lastName;
    private UUID groupId;
    private List<UUID> executedTasks;

    public Student(UUID id, String firstName, String lastName) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.executedTasks = new ArrayList<>();
    }
}
