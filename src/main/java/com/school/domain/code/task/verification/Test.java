package com.school.domain.code.task.verification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "test")
public class Test {
    @Id
    @GeneratedValue
    private String id;
    private String requirementId;

    private String programInput;
    private String expectedOutput;

    public Test() {}

    public Test(String requirementId, String programInput, String expectedOutput) {
        this.requirementId = requirementId;
        this.programInput = programInput;
        this.expectedOutput = expectedOutput;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public String getProgramInput() {
        return programInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
