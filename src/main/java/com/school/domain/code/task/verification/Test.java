package com.school.domain.code.task.verification;

import javax.persistence.*;

import static com.school.domain.code.task.verification.Test.Type.PROGRAM_OUTPUT;

@Entity(name = "test")
public class Test {
    @Id
    @GeneratedValue
    private String id;
    private String requirementId;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String programInput;
    private String expectedOutput;

    public Test() {}

    private Test(Type type, String requirementId, String programInput, String expectedOutput) {
        this.type = type;
        this.requirementId = requirementId;
        this.programInput = programInput;
        this.expectedOutput = expectedOutput;
    }

    public static Test aProgramOutputTest(String requirementId, String programInput, String expectedOutput) {
        return new Test(PROGRAM_OUTPUT, requirementId, programInput, expectedOutput);
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

    public Type getType() {
        return type;
    }

    public enum Type {
        PROGRAM_OUTPUT,
        METHOD_EXISTS,
        METHOD_EXECUTION
    }
}
