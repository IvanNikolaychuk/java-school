package com.school.domain.code.program;

import static com.school.domain.code.program.Compilation.noCompilationErrors;

public class ProgramExecutionResult {
    private String output;
    private Compilation compilation;

    private ProgramExecutionResult(Compilation compilation, String output) {
        this.compilation = compilation;
        this.output = output;
    }

    static ProgramExecutionResult withFailedCompilation(Compilation compilation) {
        return new ProgramExecutionResult(compilation, "");
    }

    static ProgramExecutionResult withPassedCompilation(String programOutput) {
        return new ProgramExecutionResult(noCompilationErrors(), programOutput);
    }

    public String getOutput() {
        return output;
    }

    public Compilation getCompilation() {
        return compilation;
    }
}
