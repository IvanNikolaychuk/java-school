package com.school.app;

import com.school.domain.code.program.*;
import com.school.domain.code.program.javaclass.PotentialJavaClass;

public class CodeRunner {
    private static final String ROOT_DIR_PATH = System.getProperty("java.io.tmpdir") + "academy";
    private final ProgramExecutor programExecutor;

    public CodeRunner() {
        this.programExecutor = new ProgramExecutor();
    }

    public ProgramExecutionResult run(String code, String programInput) throws Exception {
        Environment environment = new Environment(ROOT_DIR_PATH);
        Program program = new Program(environment, new PotentialJavaClass(environment, code), programInput);

        return programExecutor.execute(program);
    }
}
