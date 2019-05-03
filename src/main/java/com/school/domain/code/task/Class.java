package com.school.domain.code.task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "classes")
public class Class {
    @Id
    @GeneratedValue
    private String id;

    private String name;
    private String taskId;
    private String code;

    public Class() {}

    public Class(String code, String taskId, String name) {
        this.code = code;
        this.taskId = taskId;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getTaskId() {
        return taskId;
    }
}

