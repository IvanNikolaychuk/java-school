package com.school.domain.code.runner;

import com.school.domain.code.javaclass.ValidJavaClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMainMethodRunner {
    private static final String SEPARATOR = ".";

    public void run(ValidJavaClass validJavaClass) throws Exception {
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
