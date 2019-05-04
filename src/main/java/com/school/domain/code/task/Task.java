package com.school.domain.code.task;

import com.school.domain.code.task.verification.Requirement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "tasks")
public class Task {
    @Id
    private String id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Class.class, mappedBy = "taskId")
    private List<Class> classes;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Requirement.class, mappedBy = "taskId")
    private Set<Requirement> requirements;

    public Task() {}

    public Task(String title, List<Class> classes, Set<Requirement> requirements) {
        this.title = title;
        this.classes = classes;
        this.requirements = requirements;
    }

    public Task(String title, Set<Requirement> requirements) {
        this(title, new ArrayList<>(), requirements);
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

    public Set<Requirement> getRequirements() {
        return requirements;
    }
}
