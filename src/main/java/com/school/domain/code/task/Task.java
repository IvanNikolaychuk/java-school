package com.school.domain.code.task;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "tasks")
public class Task {
    @Id
    private String id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Class.class, mappedBy = "taskId")
    private List<Class> classes;

    @ElementCollection
    @CollectionTable(name="conditions", joinColumns=@JoinColumn(name="task_id"))
    // TODO: should be complex object and have id
    private List<String> conditions;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Specification.class, mappedBy = "taskId")
    private Set<Specification> specifications;

    public Task() {}

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

    public Set<Specification> getSpecifications() {
        return specifications;
    }
}
