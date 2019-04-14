package com.school.domain.code;

public class JavaClass {
    private String content;
    private String classPackage;
    private String name;

    public JavaClass(String name, String content, String classPackage) {
        this.name = name;
        this.content = content;
        this.classPackage = classPackage;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getClassPackage() {
        return classPackage;
    }

    public String getFullPath(String delimiter) {
        return getClassPackage() + delimiter + getName() + getExtension();
    }

    private String getExtension() {
        return ".java";
    }
}
