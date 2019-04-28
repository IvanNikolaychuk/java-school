package com.school.domain.code.program;

import com.school.domain.code.javaclass.Class;

import java.util.Optional;

public class Program {
    private final Class aClass;
    private final String input;
    private final String rootDir;
    private final String taskId;

    Program(String rootDir, String taskId, Class aClass, String input) {
        this.rootDir = rootDir;
        this.taskId = taskId;
        this.aClass = aClass;
        this.input = input;
    }

    Class getAClass() {
        return aClass;
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


