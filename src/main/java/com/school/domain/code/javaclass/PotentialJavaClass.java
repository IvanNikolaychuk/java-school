package com.school.domain.code.javaclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PotentialJavaClass implements Class {
    private final String rootDir;
    private final String taskId;
    private final String code;

    public PotentialJavaClass(String rootDir, String taskId, String code) {
        this.rootDir = rootDir;
        this.taskId = taskId;
        this.code = code;
    }

    @Override
    public String getFullPathWithExtension(String separator) {
        return rootDir + separator + taskId + separator + getName() + ".java";
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
    public String getRootDir() {
        return rootDir;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }
}
