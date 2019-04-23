package com.school.domain.code;

import com.school.domain.code.creator.FileFactory;
import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.runner.JavaMainMethodRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.*;

public class JavaClassExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaClassExecutor.class);

    private final FileFactory javaClassFileFactory;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaMainMethodRunner javaClassRunner;

    public JavaClassExecutor(String rootDir) {
        this.javaClassFileFactory = new FileFactory(rootDir);
        this.javaClassCompiler = new JavaClassCompiler(rootDir);
        this.javaClassRunner = new JavaMainMethodRunner(rootDir);
    }

    public void execute(JavaClass javaClass) throws Exception {
        javaClassFileFactory.create(javaClass.getFullPathWithExtension(separator), javaClass.getContent());
        CompilationResult result = javaClassCompiler.compile(javaClass);
        if (result.isSuccessful()) {
            javaClassRunner.run(javaClass);
        } else {
            LOGGER.error("There is an error during compilation. \n {} ", result.details());
        }
    }

}
