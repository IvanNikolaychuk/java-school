package com.school.domain.code.task.verification;

import com.school.domain.code.program.Compilation;

import java.util.Map;

public class VerificationSummary {
    private final boolean passed;
    private final Compilation compilation;
    private Map<String, VerificationResult> results;

    private VerificationSummary(boolean passed, Compilation compilation, Map<String, VerificationResult> results) {
        this.passed = passed;
        this.compilation = compilation;
        this.results = results;
    }

    public static VerificationSummary ok(Map<String, VerificationResult> results) {
        return new VerificationSummary(true, Compilation.noCompilationErrors(), results);
    }

    public static VerificationSummary failure(Map<String, VerificationResult> results, Compilation compilation) {
        return new VerificationSummary(false, compilation, results);
    }

    public boolean isPassed() {
        return passed;
    }

    public Compilation getCompilation() {
        return compilation;
    }

    public Map<String, VerificationResult> getResults() {
        return results;
    }
}
