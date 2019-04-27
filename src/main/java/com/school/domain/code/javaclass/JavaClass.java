package com.school.domain.code.javaclass;

public class JavaClass {
    private String content;
    private Package classPackage;
    private String name;
    private String taskId;

    public JavaClass(String taskId, Package classPackage, String name, String content) {
        this.taskId = taskId;
        this.classPackage = classPackage;
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getPackages(String separator) {
        return classPackage.fullPath(separator);
    }

    public String getFullPath(String separator) {
        return getPackages(separator) + separator + getName();
    }

    public String getFullPathWithExtension(String separator) {
        return getFullPath(separator) + getExtension();
    }

    private String getExtension() {
        return ".java";
    }

    public String getTaskId() {
        return taskId;
    }
}
