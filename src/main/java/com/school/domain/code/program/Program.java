package com.school.domain.code.program;

import com.school.domain.code.javaclass.JavaClass;

import java.util.Optional;

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

    public String getTaskId() {
        return taskId;
    }
}


