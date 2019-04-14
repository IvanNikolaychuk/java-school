package com.school.domain.code.runner;

import com.school.domain.code.JavaClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class JavaMainMethodRunner {
    private static final String SEPARATOR = ".";

    private final String rootDir;

    public JavaMainMethodRunner(String rootDir) {
        this.rootDir = rootDir;
    }

    public void run(JavaClass javaClass) throws Exception {
        aClassLoader().loadClass(javaClass.getFullPath(SEPARATOR))
                .getMethod("main", String[].class)
                .invoke(null, emptyArgs());
    }

    private URLClassLoader aClassLoader() throws MalformedURLException {
        ClassLoader classLoader = JavaMainMethodRunner.class.getClassLoader();
        return new URLClassLoader(new URL[]{Paths.get(this.rootDir).toUri().toURL()}, classLoader);
    }

    private Object[] emptyArgs() {
        final Object[] args = new Object[1];
        args[0] = new String[0];

        return args;
    }
}
