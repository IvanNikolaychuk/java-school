package com.school.domain.code;

import com.school.domain.code.creator.FileFactory;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;

    public ProgramExecutor(String rootDirPath) {
        this.javaClassExecutor = new JavaClassExecutor(rootDirPath);
    }

    public void execute(Program program) throws Exception {
        if (program.getInput().isPresent()) {
            aFileFactory(program.getRootDir()).create(program.relativePathToInput(), program.getInput().get());
        }
        javaClassExecutor.execute(program.getJavaClass());
    }

    private FileFactory aFileFactory(String rootDir) {
        return new FileFactory(rootDir);
    }
}
