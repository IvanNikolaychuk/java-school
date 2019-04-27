package com.school.domain.code.javaclass;

public class JavaClassExecutionResult {
    private final boolean compilationSucceed;
    private final String errorsDuringCompilation;

    private JavaClassExecutionResult(boolean compilationSucceed, String errorsDuringCompilation) {
        this.compilationSucceed = compilationSucceed;
        this.errorsDuringCompilation = errorsDuringCompilation;
    }

    public static JavaClassExecutionResult compilationFailed(String error) {
        return new JavaClassExecutionResult(false, error);
    }

    public static JavaClassExecutionResult successfullyCompiled() {
        return new JavaClassExecutionResult(true, "");
    }

    public boolean isCompilationSucceed() {
        return compilationSucceed;
    }

    public String getErrorsDuringCompilation() {
        return errorsDuringCompilation;
    }
}
