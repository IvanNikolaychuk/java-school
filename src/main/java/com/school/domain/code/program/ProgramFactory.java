package com.school.domain.code.program;

import com.school.domain.code.javaclass.JavaClass;
import com.school.domain.code.javaclass.JavaClassFactory;

public class ProgramFactory {
    private final JavaClassFactory javaClassFactory;

    public ProgramFactory() {
        this.javaClassFactory = new JavaClassFactory();
    }

    public Program create(String taskId, String content, String input, String rootDir) {
        JavaClass javaClass = javaClassFactory.create(rootDir, taskId, content);
        return new Program(taskId, javaClass, input, rootDir);
    }
}
