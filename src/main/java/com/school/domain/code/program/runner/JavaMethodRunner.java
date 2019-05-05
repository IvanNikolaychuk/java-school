package com.school.domain.code.program.runner;

import com.school.domain.code.program.Method;
import com.school.domain.code.program.compiler.JavaClassCompiler;
import com.school.domain.code.program.javaclass.ValidJavaClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMethodRunner {
    private static final String SEPARATOR = ".";
    private final JavaClassCompiler javaClassCompiler;

    public JavaMethodRunner() {
        this.javaClassCompiler = new JavaClassCompiler();
    }

    public Object run(ValidJavaClass validJavaClass, Method method) throws Exception {
        javaClassCompiler.compile(validJavaClass);

        return aClassLoaderFor(validJavaClass)
                .loadClass(validJavaClass.getRelativePathWithoutExtension(SEPARATOR))
                .getMethod(method.getName(), method.getParameterTypes())
                .invoke(null, method.getArgs());
    }

    private URLClassLoader aClassLoaderFor(ValidJavaClass validJavaClass) throws MalformedURLException {
        ClassLoader classLoader = JavaMethodRunner.class.getClassLoader();
        return new URLClassLoader(new URL[]{Paths.get(validJavaClass.getDirectory()).toUri().toURL()}, classLoader);
    }
}
