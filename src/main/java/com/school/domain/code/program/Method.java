package com.school.domain.code.program;

public class Method {
    private String name;
    private Class[] parameterTypes;
    private Object[] args;

    public Method(String name, Class[] parameterTypes, Object[] args) {
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.args = args;
    }

    public static Method mainMethod() {
        final Class[] parameterTypes = new Class[1];
        parameterTypes[0] = String[].class;

        final Object[] args = new Object[1];
        args[0] = new String[0];

        return new Method("main", parameterTypes, args);
    }

    public String getName() {
        return name;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
