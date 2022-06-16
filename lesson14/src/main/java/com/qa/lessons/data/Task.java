package com.qa.lessons.data;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Task extends ObjectWithData {

    private String name;
    private UUID groupId;
    private List<UUID> taskFinishedStudentIds;

    public Task(UUID id, String name, UUID groupId) {
        super.setId(id);
        this.name = name;
        this.groupId = groupId;
        this.taskFinishedStudentIds = new ArrayList<>();
    }
}
