package com.school.domain.code.javaclass;

import java.util.Arrays;
import java.util.List;

public class Package {
    private final List<String> packages;

    public Package(String ...packages) {
        this.packages = Arrays.asList(packages);
    }

    String fullPath(String separator) {
        return String.join(separator, packages);
    }
}
