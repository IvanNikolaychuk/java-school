package com.school.domain.code.program.javaclass;

import com.school.domain.code.program.Environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PotentialJavaClass implements Class {
    private final Environment environment;
    private final String code;

    public PotentialJavaClass(Environment environment, String code) {
        this.environment = environment;
        this.code = code;
    }

    // TODO: Package is not included here. Would be good to have it.
    @Override
    public String getFullPathWithExtension(String separator) {
        return environment.getRootDir() + separator + getName() + ".java";
    }

    // TODO: hide this into factory and re-validate
    private String getName() {
        Pattern compile = Pattern.compile("(?<=\\n|\\A)(?:public\\s)?(class|interface|enum)\\s([^\\n\\s]*)");
        Matcher matcher = compile.matcher(code);
        matcher.find();
        return matcher.group(2).replaceAll("[^A-Za-z0-9]", "");
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }
}
