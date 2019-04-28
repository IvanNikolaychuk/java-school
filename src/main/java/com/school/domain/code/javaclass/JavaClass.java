package com.school.domain.code.javaclass;

public class JavaClass {
    private String rootDir;
    private String taskId;

    private String content;
    private Package classPackage;
    private String name;

    public JavaClass(String rootDir, String taskId, Package classPackage, String name, String content) {
        this.rootDir = rootDir;
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

    public String getDirectory(String separator) {
        return rootDir + separator + taskId ;
    }

    public String getPackages(String separator) {
        return classPackage.fullPath(separator);
    }

    public String getRelativePathWithoutExtension(String separator) {
        return getPackages(separator) + separator + getName();
    }

    public String getFullPathWithExtension(String separator) {
        return getDirectory(separator) + separator + getRelativePathWithoutExtension(separator) + ".java";
    }

    public String getTaskId() {
        return taskId;
    }
}
