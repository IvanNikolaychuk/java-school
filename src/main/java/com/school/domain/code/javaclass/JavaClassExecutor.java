package com.school.domain.code.javaclass;

import com.school.domain.code.file.FileFactory;
import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.runner.JavaMainMethodRunner;

import java.nio.file.Paths;

import static java.io.File.*;

public class JavaClassExecutor {
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;

    public JavaClassExecutor() {
        this.javaClassCompiler = new JavaClassCompiler();
        this.javaClassRunner = new JavaMainMethodRunner();
    }

    public JavaClassExecutionResult execute(JavaClass javaClass) throws Exception {
        CompilationResult result = javaClassCompiler.compile(javaClass);
        if (result.isSuccessful()) {
            javaClassRunner.run(javaClass);
            return JavaClassExecutionResult.successfullyCompiled();
        } else {
            return JavaClassExecutionResult.compilationFailed(result.details());
        }
    }
}
