package com.school.app;

import com.school.domain.code.Program;
import com.school.domain.code.ProgramExecutionResult;
import com.school.domain.code.ProgramExecutor;
import com.school.domain.code.ProgramFactory;

public class CodeRunner {
    private final String rootDir;
    private final ProgramFactory programFactory;
    private final ProgramExecutor programExecutor;

    public CodeRunner(String rootDir) {
        this.programExecutor = new ProgramExecutor(rootDir);
        this.programFactory = new ProgramFactory();
        this.rootDir = rootDir;
    }

    public ProgramExecutionResult run(String code, String input) throws Exception {
        Program program = programFactory.create(code, input, rootDir);
        return programExecutor.execute(program);
    }
}
