package com.school.domain.code;

import com.school.domain.code.creator.FileFactory;

import static java.io.File.separator;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;

    public ProgramExecutor(String rootDirPath) {
        this.javaClassExecutor = new JavaClassExecutor(rootDirPath);
    }

    public void execute(Program program) throws Exception {
        if (program.getInput().isPresent()) {
            String path = program.getJavaClass().getPackages(separator) + separator + "input.txt";
            aFileFactory(program.getRootDir()).create(path, program.getInput().get());
        } else {
            javaClassExecutor.execute(program.getJavaClass());

        }
    }

    private FileFactory aFileFactory(String rootDir) {
        return new FileFactory(rootDir);
    }
}
