package com.school.domain.code.javaclass;

public interface Class {
    String getFullPathWithExtension(String separator);

    String getCode();

    String getRootDir();

    String getTaskId();
}
