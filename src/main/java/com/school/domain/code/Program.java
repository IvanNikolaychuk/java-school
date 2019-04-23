package com.school.domain.code;

import java.util.Optional;

public class Program {
    private final JavaClass javaClass;
    private final String input;
    private final String rootDir;

    public Program(JavaClass javaClass, String input, String rootDir) {
        this.javaClass = javaClass;
        this.input = input;
        this.rootDir = rootDir;
    }

    public JavaClass getJavaClass() {
        return javaClass;
    }

    public Optional<String> getInput() {
        return input.isEmpty() ? Optional.empty() : Optional.of(input);
    }

    public String getRootDir() {
        return rootDir;
    }
}
