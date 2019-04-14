package com.school.domain.code.creator;

import com.school.domain.code.JavaClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaClassFactory {
    private final String path;

    public JavaClassFactory(String path) {
        this.path = path;
    }

    public void create(JavaClass javaClass) throws Exception {
        Path classPath = Paths.get(this.path,  javaClass.getNameWithExtension());
        Files.write(classPath, javaClass.getContent().getBytes());
    }

}
