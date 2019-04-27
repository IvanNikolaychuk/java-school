package com.school.domain.code.program;

import com.school.domain.code.file.FileFactory;
import com.school.domain.code.javaclass.JavaClassExecutionResult;
import com.school.domain.code.javaclass.JavaClassExecutor;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ProgramExecutor {
    private final JavaClassExecutor javaClassExecutor;

    public ProgramExecutor(String rootDirPath) {
        this.javaClassExecutor = new JavaClassExecutor(rootDirPath);
    }

    public ProgramExecutionResult execute(Program program) throws Exception {
        String inputContent = program.getInput().isPresent() ? program.getInput().get() : "";
        aFileFactory(program.getRootDir()).create(program.relativePathToInput(), inputContent);
        JavaClassExecutionResult javaClassExecutionResult = javaClassExecutor.execute(program.getJavaClass());

        if (javaClassExecutionResult.isCompilationSucceed()) {
            byte[] outputContent = Files.readAllBytes(Paths.get(program.fullPathToOutput()));
            return new ProgramExecutionResult(javaClassExecutionResult, new String(outputContent));
        } else {
            return new ProgramExecutionResult(javaClassExecutionResult, "");
        }
    }

    private FileFactory aFileFactory(String rootDir) {
        return new FileFactory(rootDir);
    }
}
