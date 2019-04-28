package com.school.domain.code.program;

import com.school.domain.code.javaclass.PotentialJavaClass;

public class ProgramFactory {
    public Program create(String rootDir, String taskId, String content, String input) {
        return new Program(rootDir, taskId, new PotentialJavaClass(rootDir, taskId, content), input);
    }
}
