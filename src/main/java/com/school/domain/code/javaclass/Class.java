package com.school.domain.code.javaclass;

public interface Class {
    String getRootDir();

    String getFullPathWithExtension(String separator);

    String getTaskId();

    String getCode();
}
