package com.school.app;

import com.school.domain.code.rest.Class;

public class CodeLoader {
    public Class load(String taskId) {
        String code = "package javaschool.task.section01.task01;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "    }\n" +
                "}";

        return new Class(code, "Main");
    }
}
