package com.school.domain.code.program;

import com.school.domain.code.javaclass.JavaClass;
import com.school.domain.code.javaclass.JavaClassFactory;

public class ProgramFactory {
    private final JavaClassFactory javaClassFactory;

    public ProgramFactory() {
        this.javaClassFactory = new JavaClassFactory();
    }

    public Program create(String rootDir, String taskId, String content, String input) {
        JavaClass javaClass = javaClassFactory.create(rootDir, taskId, content);
        return new Program(rootDir, taskId, javaClass, input);
    }
}
