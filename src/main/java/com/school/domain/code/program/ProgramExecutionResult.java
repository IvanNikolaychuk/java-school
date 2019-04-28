package com.school.domain.code.program;

import com.school.domain.code.compiler.CompilationResult;

import static com.school.domain.code.compiler.CompilationResult.noCompilationErrors;

public class ProgramExecutionResult {
    private String output;
    private CompilationResult compilationResult;

    ProgramExecutionResult(CompilationResult compilationResult, String output) {
        this.compilationResult = compilationResult;
        this.output = output;
    }

    public static ProgramExecutionResult withFailedCompilation(CompilationResult compilationResult) {
        return new ProgramExecutionResult(compilationResult, "");
    }

    public static ProgramExecutionResult withPassedCompilation(String programOutput) {
        return new ProgramExecutionResult(noCompilationErrors(), programOutput);
    }

    public String getOutput() {
        return output;
    }

    public CompilationResult getCompilationResult() {
        return compilationResult;
    }
}
