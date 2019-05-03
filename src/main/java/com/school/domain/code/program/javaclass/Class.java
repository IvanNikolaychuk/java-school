package com.school.domain.code.program.javaclass;

import com.school.domain.code.program.Environment;

public interface Class {
    Environment getEnvironment();

    String getFullPathWithExtension(String separator);

    String getCode();
}
