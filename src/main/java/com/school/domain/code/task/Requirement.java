package com.school.domain.code.task;

import com.school.domain.code.task.verification.Specification;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@Entity(name = "requirements")
public class Requirement {
    @Id
    @GeneratedValue
    private String id;

    private String taskId;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Specification.class, mappedBy = "requirementId")
    private List<Specification> specifications;

    public Requirement() {}

    public Requirement(String name, Specification ...specifications) {
        this.name = name;
        this.specifications = Arrays.asList(specifications);
    }

    public String getName() {
        return name;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public String getTaskId() {
        return taskId;
    }
}
