package com.school.domain.code.program;

import static com.school.domain.code.program.Compilation.noCompilationErrors;

public class ExecutionResult {
    private String output;
    private Compilation compilation;

    private ExecutionResult(Compilation compilation, String output) {
        this.compilation = compilation;
        this.output = output;
    }

    static ExecutionResult withFailedCompilation(Compilation compilation) {
        return new ExecutionResult(compilation, "");
    }

    static ExecutionResult withPassedCompilation(String programOutput) {
        return new ExecutionResult(noCompilationErrors(), programOutput);
    }

    public boolean hasCompilationFailed() {
        return !compilation.getProblems().isEmpty();
    }

    public String getOutput() {
        return output;
    }

    public Compilation getCompilation() {
        return compilation;
    }
}
