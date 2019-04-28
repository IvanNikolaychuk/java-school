package com.school.domain.code.runner;

import com.school.domain.code.javaclass.JavaClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMainMethodRunner {
    private static final String SEPARATOR = ".";

    public void run(JavaClass javaClass) throws Exception {
        aClassLoaderFor(javaClass).loadClass(javaClass.getRelativePathWithoutExtension(SEPARATOR))
                .getMethod("main", String[].class)
                .invoke(null, emptyArgs());
    }

    private URLClassLoader aClassLoaderFor(JavaClass javaClass) throws MalformedURLException {
        ClassLoader classLoader = JavaMainMethodRunner.class.getClassLoader();
        return new URLClassLoader(new URL[]{Paths.get(javaClass.getDirectory(File.separator)).toUri().toURL()}, classLoader);
    }

    private Object[] emptyArgs() {
        final Object[] args = new Object[1];
        args[0] = new String[0];

        return args;
    }
}
