package com.school.domain.code;

public class ProgramExecutionResult {
    private String output;
    private JavaClassExecutionResult javaClassExecutionResult;

    ProgramExecutionResult(JavaClassExecutionResult javaClassExecutionResult, String output) {
        this.javaClassExecutionResult = javaClassExecutionResult;
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public JavaClassExecutionResult getJavaClassExecutionResult() {
        return javaClassExecutionResult;
    }
}
