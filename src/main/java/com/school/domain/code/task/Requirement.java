package com.school.domain.code.task;

import com.school.domain.code.task.verification.Test;

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

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Test.class, mappedBy = "requirementId")
    private List<Test> tests;

    public Requirement() {}

    public Requirement(String name, Test... tests) {
        this.name = name;
        this.tests = Arrays.asList(tests);
    }

    public String getName() {
        return name;
    }

    public List<Test> getTests() {
        return tests;
    }

    public String getTaskId() {
        return taskId;
    }
}
