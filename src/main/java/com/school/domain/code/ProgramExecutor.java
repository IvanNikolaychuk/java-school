package com.school.domain.code;

import com.school.domain.code.creator.FileFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;

    public ProgramExecutor(String rootDirPath) {
        this.javaClassExecutor = new JavaClassExecutor(rootDirPath);
    }

    public ProgramExecutionResult execute(Program program) throws Exception {
        if (program.getInput().isPresent()) {
            aFileFactory(program.getRootDir()).create(program.relativePathToInput(), program.getInput().get());
        }
        JavaClassExecutionResult javaClassExecutionResult = javaClassExecutor.execute(program.getJavaClass());

        if (javaClassExecutionResult.isCompilationSucceed()) {
            byte[] content = Files.readAllBytes(Paths.get(program.fullPathToOutput()));
            return new ProgramExecutionResult(javaClassExecutionResult, new String(content));
        } else {
            return new ProgramExecutionResult(javaClassExecutionResult, "");
        }
    }

    private FileFactory aFileFactory(String rootDir) {
        return new FileFactory(rootDir);
    }
}
