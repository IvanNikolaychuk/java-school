package com.school.domain.code.task;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tasks")
public class Task {
    @Id
    private String id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Class.class, mappedBy = "taskId")
    private List<Class> classes;

    @ElementCollection
    @CollectionTable(name="conditions", joinColumns=@JoinColumn(name="task_id"))
    private List<String> conditions;

    public Task() {}

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
