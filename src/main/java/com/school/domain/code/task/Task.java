package com.school.domain.code.task;

import java.util.List;

public class Task {
    private String id;
    private String title;
    private List<Class> classes;
    private List<String> conditions;

    Task(String id,
         String title,
         List<Class> classes,
         List<String> conditions) {
        this.id = id;
        this.title = title;
        this.classes = classes;
        this.conditions = conditions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public List<String> getConditions() {
        return conditions;
    }
}
