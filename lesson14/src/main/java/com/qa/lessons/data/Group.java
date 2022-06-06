package com.qa.lessons.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Group extends ObjectWithData{

    private UUID groupLeaderId; //In case when need to change the leader it's much easier to change id than copy created object instead of previous object
    private List<UUID> students;
    private List<UUID> tasks;

    public Group(UUID id, UUID groupLeaderId) {
        super.setId(id);
        this.groupLeaderId = groupLeaderId;
        this.students = new ArrayList<>();
        this.students.add(groupLeaderId);
        this.tasks = new ArrayList<>();
    }
}
