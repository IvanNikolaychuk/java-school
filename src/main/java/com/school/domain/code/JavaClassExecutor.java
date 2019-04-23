package com.school.domain.code;

import com.school.domain.code.creator.JavaClassFileCreator;
import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.runner.JavaMainMethodRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaClassExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaClassExecutor.class);

    private final JavaClassFileCreator javaClassFileCreator;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;

    public JavaClassExecutor(String rootDir) {
        this.javaClassFileCreator = new JavaClassFileCreator(rootDir);
        this.javaClassCompiler = new JavaClassCompiler(rootDir);
        this.javaClassRunner = new JavaMainMethodRunner(rootDir);
    }

    public void execute(JavaClass javaClass) throws Exception {
        javaClassFileCreator.create(javaClass);
        CompilationResult result = javaClassCompiler.compile(javaClass);
        if (result.isSuccessful()) {
            javaClassRunner.run(javaClass);
        } else {
            LOGGER.error("There is an error during compilation. \n {} ", result.details());
        }
    }

}
