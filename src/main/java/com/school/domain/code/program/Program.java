package com.school.domain.code.program;

import com.school.domain.code.javaclass.JavaClass;

import java.util.Optional;

import static java.io.File.separator;

public class Program {
    private final JavaClass javaClass;
    private final String input;
    private final String rootDir;

    Program(JavaClass javaClass, String input, String rootDir) {
        this.javaClass = javaClass;
        this.input = input;
        this.rootDir = rootDir;
    }

    JavaClass getJavaClass() {
        return javaClass;
    }

    Optional<String> getInput() {
        return input.isEmpty() ? Optional.empty() : Optional.of(input);
    }

    String getRootDir() {
        return rootDir;
    }

    String relativePathToInput() {
        return getJavaClass().getPackages(separator) + separator + "input.txt";
    }

    String fullPathToInput() {
        return rootDir + separator + relativePathToInput();
    }

    private String relativePathToOutput() {
        return getJavaClass().getPackages(separator) + separator + "output.txt";
    }

    String fullPathToOutput() {
        return rootDir + separator + relativePathToOutput();
    }

    Program replaceJavaClass(JavaClass javaClass) {
        return new Program(javaClass, input, rootDir);
    }

}


