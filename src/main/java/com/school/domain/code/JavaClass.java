package com.school.domain.code;

public class JavaClass {
    private String content;
    private String name;

    public JavaClass(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return ".java";
    }

    public String getNameWithExtension() {
        return getName() + getExtension();
    }
}
