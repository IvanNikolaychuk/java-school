package com.school.domain.code.task.verification;

import com.school.domain.code.program.ExecutionResult;

public class VerificationResult {
    private final boolean passed;
    private final ExecutionResult executionResult;
    private final String programInput;
    private final String expectedOutput;

    private VerificationResult(boolean passed, ExecutionResult executionResult, String programInput, String expectedOutput) {
        this.passed = passed;
        this.executionResult = executionResult;
        this.programInput = programInput;
        this.expectedOutput = expectedOutput;
    }

    public static VerificationResult ok() {
        return new VerificationResult(true, null, null, null);
    }

    public static VerificationResult failure(ExecutionResult executionResult, String programInput, String expectedOutput) {
        return new VerificationResult(false, executionResult, programInput, expectedOutput);
    }

    public boolean isPassed() {
        return passed;
    }

    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public String getProgramInput() {
        return programInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
