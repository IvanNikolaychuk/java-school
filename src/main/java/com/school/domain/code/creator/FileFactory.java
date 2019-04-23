package com.school.domain.code.creator;

import com.school.domain.code.JavaClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

public class FileFactory {
    private final String rootDir;

    public FileFactory(String rootDir) {
        this.rootDir = rootDir;
    }

    public void create(String fullPathToFile, String content) throws Exception {
        String dir = fullPathToFile.substring(0, fullPathToFile.lastIndexOf(separator));
        Files.createDirectories(Paths.get(rootDir, dir));

        Path fullPathToClass = Paths.get(rootDir, fullPathToFile);
        Files.write(fullPathToClass, content.getBytes());
    }
}
