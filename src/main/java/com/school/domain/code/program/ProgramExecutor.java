package com.school.domain.code.program;

import com.school.domain.code.file.InputOutputHelper;
import com.school.domain.code.javaclass.JavaClassExecutionResult;
import com.school.domain.code.javaclass.JavaClassExecutor;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;
    private final InputOutputHelper inputOutputHelper;

    public ProgramExecutor(String rootDirPath) {
        this.javaClassExecutor = new JavaClassExecutor(rootDirPath);
        this.inputOutputHelper = new InputOutputHelper(rootDirPath);
    }

    public ProgramExecutionResult execute(Program program) throws Exception {
        inputOutputHelper.createInputOutputFiles(program);
        JavaClassExecutionResult javaClassExecutionResult = javaClassExecutor.execute(program.getJavaClass());

        if (javaClassExecutionResult.isCompilationSucceed()) {
            return new ProgramExecutionResult(javaClassExecutionResult, inputOutputHelper.readOutputContent(program));
        } else {
            return new ProgramExecutionResult(javaClassExecutionResult, "");
        }
    }
}
