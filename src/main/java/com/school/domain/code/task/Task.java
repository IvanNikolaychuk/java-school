package com.school.domain.code.task;

import com.school.domain.code.task.verification.Specification;

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

    public Task(String title, List<Class> classes, List<String> conditions, Set<Specification> specifications) {
        this.title = title;
        this.classes = classes;
        this.conditions = conditions;
        this.specifications = specifications;
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

    public Set<Specification> getSpecifications() {
        return specifications;
    }
}
