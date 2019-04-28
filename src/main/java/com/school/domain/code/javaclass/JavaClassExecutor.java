package com.school.domain.code.javaclass;

import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.file.FileFactory;
import com.school.domain.code.runner.JavaMainMethodRunner;

import java.io.File;

import static java.io.File.separator;

public class JavaClassExecutor {
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;
    private final JavaClassFactory javaClassFactory;

    public JavaClassExecutor() {
        this.javaClassCompiler = new JavaClassCompiler();
        this.javaClassRunner = new JavaMainMethodRunner();
        this.javaClassFactory = new JavaClassFactory();
    }

    public JavaClassExecutionResult execute(Class aClass) throws Exception {
        CompilationResult result = javaClassCompiler.compile(aClass);
        if (result.isSuccessful()) {
            ValidJavaClass validJavaClass = javaClassFactory.create(aClass.getRootDir(), aClass.getTaskId(), aClass.getCode());
            javaClassCompiler.compile(validJavaClass);
            javaClassRunner.run(validJavaClass);
            return JavaClassExecutionResult.successfullyCompiled();
        } else {
            return JavaClassExecutionResult.compilationFailed(result.details());
        }
    }
}
