package com.school.domain.code.program.javaclass;

import com.school.domain.code.program.Environment;

public class ValidJavaClass implements Class {
    private Environment environment;
    private Package classPackage;

    private String name;
    private String code;

    private ValidJavaClass(Environment environment, Package classPackage, String name, String code) {
        this.environment = environment;
        this.classPackage = classPackage;
        this.name = name;
        this.code = code;
    }

    public String getDirectory() {
        return environment.getRootDir();
    }

    public String getPackages(String separator) {
        return classPackage.fullPath(separator);
    }

    public String getRelativePathWithoutExtension(String separator) {
        return getPackages(separator) + separator + getName();
    }

    @Override
    public String getFullPathWithExtension(String separator) {
        return getDirectory() + separator + getRelativePathWithoutExtension(separator) + ".java";
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    public static final class JavaClassBuilder {
        private Environment environment;
        private String code;
        private Package classPackage;
        private String name;

        private JavaClassBuilder() {
        }

        public static JavaClassBuilder aJavaClass() {
            return new JavaClassBuilder();
        }

        public JavaClassBuilder withEnviroment(Environment environment) {
            this.environment = environment;
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

        public ValidJavaClass build() {
            return new ValidJavaClass(environment, classPackage, name, code);
        }
    }
}
