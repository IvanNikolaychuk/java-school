package com.school.domain.code.program;

import java.util.ArrayList;
import java.util.List;

public class Compilation {
    private List<String> problems;

    Compilation(List<String> problems) {
        this.problems = problems;
    }

    public static Compilation noCompilationErrors() {
        return new Compilation(new ArrayList<>());
    }

    public List<String> getProblems() {
        return problems;
    }
}
