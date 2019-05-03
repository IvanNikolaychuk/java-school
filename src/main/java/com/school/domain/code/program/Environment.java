package com.school.domain.code.program;

import java.io.File;

public class Environment {
    private final String rootDir;

    public Environment(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getRootDir() {
        return rootDir;
    }

    public String fullPathToOutput() {
        return rootDir + File.separator + "output.txt";
    }

    public String fullPathToInput() {
        return rootDir + File.separator + "input.txt";
    }
}
