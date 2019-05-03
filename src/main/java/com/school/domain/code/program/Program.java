package com.school.domain.code.program;

import com.school.domain.code.program.javaclass.PotentialJavaClass;

import java.util.Optional;

public class Program {
    private final PotentialJavaClass aClass;
    private final String input;
    private final Environment environment;

    public Program(Environment environment, PotentialJavaClass aClass, String input) {
        this.environment = environment;
        this.aClass = aClass;
        this.input = input;
    }

    PotentialJavaClass getPotentialClass() {
        return aClass;
    }

    public Optional<String> getInput() {
        return input.isEmpty() ? Optional.empty() : Optional.of(input);
    }

    public Environment getEnvironment() {
        return environment;
    }
}


