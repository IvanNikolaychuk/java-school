package com.school.domain.code.javaclass;

import com.school.domain.code.file.FileFactory;
import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.runner.JavaMainMethodRunner;

import java.nio.file.Paths;

import static java.io.File.*;

public class JavaClassExecutor {
    private final FileFactory fileFactory;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;
    private final String rootDir;

    public JavaClassExecutor(String rootDir) {
        this.fileFactory = new FileFactory();
        this.javaClassCompiler = new JavaClassCompiler(rootDir);
        this.javaClassRunner = new JavaMainMethodRunner(rootDir);
        this.rootDir = rootDir;
    }

    public JavaClassExecutionResult execute(JavaClass javaClass) throws Exception {
        createJavaFile(javaClass);
        CompilationResult result = javaClassCompiler.compile(javaClass);
        if (result.isSuccessful()) {
            javaClassRunner.run(javaClass);
            return JavaClassExecutionResult.successfullyCompiled();
        } else {
            return JavaClassExecutionResult.compilationFailed(result.details());
        }
    }

    private void createJavaFile(JavaClass javaClass) throws Exception {
        String path = rootDir + separator + javaClass.getTaskId() + separator + javaClass.getFullPathWithExtension(separator);
        fileFactory.create(path, javaClass.getContent());
    }
}
