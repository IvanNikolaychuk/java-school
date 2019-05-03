package com.school.domain.code.program;

import com.school.domain.code.program.javaclass.PotentialJavaClass;

import java.util.Optional;

public class Program {
    private final PotentialJavaClass aClass;
    private final String input;
    private final String rootDir;
    private final String taskId;

    Program(String rootDir, String taskId, PotentialJavaClass aClass, String input) {
        this.rootDir = rootDir;
        this.taskId = taskId;
        this.aClass = aClass;
        this.input = input;
    }

    PotentialJavaClass getPotentialClass() {
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


