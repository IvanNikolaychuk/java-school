package com.school.domain.code.program.runner;

import com.school.domain.code.program.compiler.JavaClassCompiler;
import com.school.domain.code.program.javaclass.ValidJavaClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMainMethodRunner {
    private static final String SEPARATOR = ".";
    private final JavaClassCompiler javaClassCompiler;

    public JavaMainMethodRunner() {
        this.javaClassCompiler = new JavaClassCompiler();
    }

    public void runMainMethod(ValidJavaClass validJavaClass) throws Exception {
        javaClassCompiler.compile(validJavaClass);

        aClassLoaderFor(validJavaClass).loadClass(validJavaClass.getRelativePathWithoutExtension(SEPARATOR))
                .getMethod("main", String[].class)
                .invoke(null, emptyArgs());
    }

    private URLClassLoader aClassLoaderFor(ValidJavaClass validJavaClass) throws MalformedURLException {
        ClassLoader classLoader = JavaMainMethodRunner.class.getClassLoader();
        return new URLClassLoader(new URL[]{Paths.get(validJavaClass.getDirectory(File.separator)).toUri().toURL()}, classLoader);
    }

    private Object[] emptyArgs() {
        final Object[] args = new Object[1];
        args[0] = new String[0];

        return args;
    }
}
