package com.school.domain.code.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

public class FileFactory {
    public void create(String fullPathToFile, String content) throws Exception {
        String dir = fullPathToFile.substring(0, fullPathToFile.lastIndexOf(separator));
        Files.createDirectories(Paths.get(dir));

        Path fullPathToClass = Paths.get(fullPathToFile);
        Files.write(fullPathToClass, content.getBytes());
    }
}
