package com.school.domain.code.javaclass;

public class JavaClass {
    private String rootDir;
    private String taskId;
    private Package classPackage;

    private String name;
    private String code;

    private JavaClass(String rootDir, String taskId, Package classPackage, String name, String code) {
        this.rootDir = rootDir;
        this.taskId = taskId;
        this.classPackage = classPackage;
        this.name = name;
        this.code = code;
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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static final class JavaClassBuilder {
        private String rootDir;
        private String taskId;
        private String code;
        private Package classPackage;
        private String name;

        private JavaClassBuilder() {
        }

        public static JavaClassBuilder aJavaClass() {
            return new JavaClassBuilder();
        }

        public JavaClassBuilder withRootDir(String rootDir) {
            this.rootDir = rootDir;
            return this;
        }

        public JavaClassBuilder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public JavaClassBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public JavaClassBuilder withClassPackage(Package classPackage) {
            this.classPackage = classPackage;
            return this;
        }

        public JavaClassBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public JavaClass build() {
            return new JavaClass(rootDir, taskId, classPackage, name, code);
        }
    }
}
