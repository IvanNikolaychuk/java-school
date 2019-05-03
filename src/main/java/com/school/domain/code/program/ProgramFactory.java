package com.school.domain.code.program;

import com.school.domain.code.program.javaclass.PotentialJavaClass;

public class ProgramFactory {
    public Program create(String rootDir, String taskId, String content, String programInput) {
        return new Program(rootDir, taskId, new PotentialJavaClass(rootDir, taskId, content), programInput);
    }
}
