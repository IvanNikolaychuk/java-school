package com.school.domain.code;

public class JavaClass {
    private String content;
    private Package classPackage;
    private String name;

    public JavaClass(Package classPackage, String name, String content) {
        this.classPackage = classPackage;
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    String getName() {
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
}
