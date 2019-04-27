package com.school.domain.code;

import java.util.Optional;

import static java.io.File.separator;

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

    public String relativePathToInput() {
        return getJavaClass().getPackages(separator) + separator + "input.txt";
    }

    public String fullPathToInput() {
        return rootDir + separator + relativePathToInput();
    }

    public String relativePathToOutput() {
        return getJavaClass().getPackages(separator) + separator + "output.txt";
    }

    public String fullPathToOutput() {
        return rootDir + separator + relativePathToOutput();
    }

    public Program withJavaClass(JavaClass javaClass) {
        return new Program(javaClass, input, rootDir);
    }

}


