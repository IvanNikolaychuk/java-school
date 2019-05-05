package com.school.domain.code.program;

public class Method {
    private String name;
    private Object[] args;

    public Method(String name, Object[] args) {
        this.name = name;
        this.args = args;
    }

    public static Method mainMethod() {
        final Object[] args = new Object[1];
        args[0] = new String[0];

        return new Method("main", args);
    }

    public String getName() {
        return name;
    }

    public Object[] getArgs() {
        return args;
    }
}
