package com.school.domain.code.program;

import com.school.domain.code.javaclass.JavaClass;

import java.util.Optional;

import static java.io.File.separator;

public class Program {
    private final JavaClass javaClass;
    private final String input;
    private final String rootDir;
    private final String taskId;

    Program(String taskId, JavaClass javaClass, String input, String rootDir) {
        this.taskId = taskId;
        this.javaClass = javaClass;
        this.input = input;
        this.rootDir = rootDir;
    }

    JavaClass getJavaClass() {
        return javaClass;
    }

    public String getRootDir() {
        return rootDir;
    }

    public Optional<String> getInput() {
        return input.isEmpty() ? Optional.empty() : Optional.of(input);
    }

    public String relativePathToInput() {
        return getJavaClass().getPackages(separator) + separator + "input.txt";
    }

    String fullPathToInput() {
        return rootDir + separator + relativePathToInput();
    }

    private String relativePathToOutput() {
        return getJavaClass().getPackages(separator) + separator + "output.txt";
    }

    public String fullPathToOutput() {
        return rootDir + separator + relativePathToOutput();
    }

    Program replaceJavaClass(JavaClass javaClass) {
        return new Program(taskId, javaClass, input, rootDir);
    }

    public String getTaskId() {
        return taskId;
    }
}


