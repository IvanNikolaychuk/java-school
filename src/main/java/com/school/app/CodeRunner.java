package com.school.app;

import com.school.domain.code.program.Program;
import com.school.domain.code.program.ProgramExecutionResult;
import com.school.domain.code.program.ProgramExecutor;
import com.school.domain.code.program.ProgramFactory;

public class CodeRunner {
    private final String rootDir;
    private final ProgramFactory programFactory;
    private final ProgramExecutor programExecutor;

    public CodeRunner(String rootDir) {
        this.programExecutor = new ProgramExecutor(rootDir);
        this.programFactory = new ProgramFactory();
        this.rootDir = rootDir;
    }

    public ProgramExecutionResult run(String taskId, String code, String programInput) throws Exception {
        Program program = programFactory.create(rootDir, taskId, code, programInput);

        return programExecutor.execute(program);
    }
}
