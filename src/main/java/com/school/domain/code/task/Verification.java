package com.school.domain.code.task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "verification")
public class Verification {
    @Id
    @GeneratedValue
    private String id;

    private String taskId;
    private String conditionId;
    private String programInput;
    private String expectedOutput;

    public Verification() {}

    public String getConditionId() {
        return conditionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getProgramInput() {
        return programInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
