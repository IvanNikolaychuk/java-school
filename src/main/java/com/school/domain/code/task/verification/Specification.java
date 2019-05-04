package com.school.domain.code.task.verification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "specification")
public class Specification {
    @Id
    @GeneratedValue
    private String id;

    private String taskId;
    private String conditionId;
    private String programInput;
    private String expectedOutput;

    public Specification() {}

    public Specification(String taskId, String conditionId, String programInput, String expectedOutput) {
        this.taskId = taskId;
        this.conditionId = conditionId;
        this.programInput = programInput;
        this.expectedOutput = expectedOutput;
    }

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
