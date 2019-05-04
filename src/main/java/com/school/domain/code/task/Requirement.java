package com.school.domain.code.task;

import com.school.domain.code.task.verification.Specification;

import javax.persistence.*;
import java.util.List;

@Entity(name = "requirements")
public class Requirement {
    @Id
    @GeneratedValue
    private String id;

    private String taskId;
    private String value;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Specification.class, mappedBy = "requirementId")
    private List<Specification> specifications;

    public Requirement() {}

    public Requirement(String taskId, String value, List<Specification> specifications) {
        this.taskId = taskId;
        this.value = value;
        this.specifications = specifications;
    }

    public String getValue() {
        return value;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public String getTaskId() {
        return taskId;
    }
}
