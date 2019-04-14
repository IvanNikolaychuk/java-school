package com.school.domain.code.runner;

import com.school.domain.code.JavaClass;
import com.school.domain.code.creator.JavaClassFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMainMethodRunner {
    private static final String SEPARATOR = ".";

    private final String path;

    public JavaMainMethodRunner(String path) {
        this.path = path;
    }

    public void run(JavaClass javaClass) throws Exception {
        aClassLoader().loadClass(getRelativePath(javaClass))
                .getMethod("main", String[].class)
                .invoke(null, emptyArgs());
    }

    private URLClassLoader aClassLoader() throws MalformedURLException {
        ClassLoader classLoader = JavaClassFactory.class.getClassLoader();
        return new URLClassLoader(new URL[]{Paths.get(this.path).toUri().toURL()}, classLoader);
    }

    private String getRelativePath(JavaClass javaClass) {
        return javaClass.getClassPackage() + SEPARATOR + javaClass.getName();
    }

    private Object[] emptyArgs() {
        final Object[] args = new Object[1];
        args[0] = new String[0];

        return args;
    }
}
