package com.school.domain.code.rest;

public class Class {
    private final String code;
    private final String name;

    public Class(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

