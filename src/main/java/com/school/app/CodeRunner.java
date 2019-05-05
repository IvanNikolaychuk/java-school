package com.school.app;

import com.school.domain.code.program.*;
import com.school.domain.code.program.javaclass.PotentialJavaClass;

public class CodeRunner {
    private static final String ROOT_DIR_PATH = System.getProperty("java.io.tmpdir") + "academy";
    private final ProgramExecutor programExecutor;

    public CodeRunner() {
        this.programExecutor = new ProgramExecutor();
    }

    public ExecutionResult run(String code, String programInput) throws Exception {
        return run(code, Method.mainMethod(), programInput);
    }

    public ExecutionResult run(String code, Method method, String programInput) throws Exception {
        Environment environment = new Environment(ROOT_DIR_PATH);
        Program program = new Program(environment, new PotentialJavaClass(environment, code), programInput);

        return programExecutor.execute(program, method);
    }
}
