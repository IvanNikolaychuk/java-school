package com.school.domain.code.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

public class FileFactory {
    public void create(String rootDir, String pathToFile, String content) throws Exception {
        String dir = pathToFile.substring(0, pathToFile.lastIndexOf(separator));
        Files.createDirectories(Paths.get(rootDir, dir));

        Path fullPath = Paths.get(rootDir, pathToFile);
        Files.write(fullPath, content.getBytes());
    }

    public void create(String fullPathToFile, String content) throws Exception {
        String dir = fullPathToFile.substring(0, fullPathToFile.lastIndexOf(separator));
        Files.createDirectories(Paths.get(dir));

        Path fullPathToClass = Paths.get(fullPathToFile);
        Files.write(fullPathToClass, content.getBytes());
    }
}
