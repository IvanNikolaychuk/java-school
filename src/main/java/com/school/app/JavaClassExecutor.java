package com.school.app;

import com.school.domain.code.JavaClass;
import com.school.domain.code.creator.JavaClassFactory;
import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.runner.JavaMainMethodRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JavaClassExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaClassExecutor.class);

    private final JavaClassFactory javaClassFactory;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;

    public JavaClassExecutor(String dirPath) {
        this.javaClassFactory = new JavaClassFactory(dirPath);
        this.javaClassCompiler = new JavaClassCompiler(dirPath);
        this.javaClassRunner = new JavaMainMethodRunner(dirPath);
    }

    public void run(JavaClass javaClass) throws Exception {
        javaClassFactory.create(javaClass);
        CompilationResult result = javaClassCompiler.compile(javaClass);
        if (result.isSuccessful()) {
            javaClassRunner.run(javaClass);
        } else {
            LOGGER.error("There is an error during compilation. \n {} ", result.details());
        }
    }

}
