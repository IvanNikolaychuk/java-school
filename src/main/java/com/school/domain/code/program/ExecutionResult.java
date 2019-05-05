package com.school.domain.code.program;

import static com.school.domain.code.program.Compilation.noCompilationErrors;

public class ExecutionResult {
    private String programOutput;
    private Compilation compilation;
    private Object methodResult;

    private ExecutionResult(Compilation compilation, String programOutput, Object methodResult) {
        this.compilation = compilation;
        this.programOutput = programOutput;
        this.methodResult = methodResult;
    }

    static ExecutionResult withFailedCompilation(Compilation compilation) {
        return new ExecutionResult(compilation, "", null);
    }

    static ExecutionResult withPassedCompilation(String programprogramOutput, Object methodResult) {
        return new ExecutionResult(noCompilationErrors(), programprogramOutput, methodResult);
    }

    public boolean hasCompilationFailed() {
        return !compilation.getProblems().isEmpty();
    }

    public String getProgramOutput() {
        return programOutput;
    }

    public Compilation getCompilation() {
        return compilation;
    }

    public Object getMethodResult() {
        return methodResult;
    }
}
