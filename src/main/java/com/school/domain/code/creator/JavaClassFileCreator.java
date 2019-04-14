package com.school.domain.code.creator;

import com.school.domain.code.JavaClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

public class JavaClassFileCreator {
    private final String rootDir;

    public JavaClassFileCreator(String rootDir) {
        this.rootDir = rootDir;
    }

    public void create(JavaClass javaClass) throws Exception {
        Files.createDirectories(Paths.get(rootDir, javaClass.getPackages(separator)));

        Path fullPathToClass = Paths.get(rootDir, javaClass.getFullPathWithExtension(separator));
        Files.write(fullPathToClass, javaClass.getContent().getBytes());
    }
}
