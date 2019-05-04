package com.school.domain.code.task.verification;

import com.school.domain.code.program.Compilation;

import java.util.Map;

public class VerificationResult {
    private final boolean passed;
    private final Compilation compilation;
    private Map<String, VerificationStatus> results;

    private VerificationResult(boolean passed, Compilation compilation, Map<String, VerificationStatus> results) {
        this.passed = passed;
        this.compilation = compilation;
        this.results = results;
    }

    public static VerificationResult ok(Map<String, VerificationStatus> results) {
        return new VerificationResult(true, Compilation.noCompilationErrors(), results);
    }

    public static VerificationResult failure(Map<String, VerificationStatus> results, Compilation compilation) {
        return new VerificationResult(false, compilation, results);
    }

    public boolean isPassed() {
        return passed;
    }

    public Compilation getCompilation() {
        return compilation;
    }

    public Map<String, VerificationStatus> getResults() {
        return results;
    }
}
