package com.school.domain.code;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;

    public ProgramExecutor(JavaClassExecutor javaClassExecutor) {
        this.javaClassExecutor = javaClassExecutor;
    }

    public void execute(Program program) throws Exception {
        javaClassExecutor.execute(program.getJavaClass());
    }
}
