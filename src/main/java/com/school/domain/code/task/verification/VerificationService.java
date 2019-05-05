package com.school.domain.code.task.verification;

import com.school.app.CodeRunner;
import com.school.domain.code.program.Compilation;
import com.school.domain.code.program.ExecutionResult;
import com.school.domain.code.task.Task;

import java.util.HashMap;
import java.util.Map;

import static com.school.domain.code.task.verification.VerificationResult.FAILED;
import static com.school.domain.code.task.verification.VerificationResult.NOT_COMPILED;
import static com.school.domain.code.task.verification.VerificationResult.PASSED;

public class VerificationService {
    private final CodeRunner codeRunner = new CodeRunner();

    public VerificationSummary verify(Task task, String code) throws Exception {
        Map<String, VerificationResult> verificationResults = new HashMap<>();

        Compilation compilationExample = Compilation.noCompilationErrors();
        for (Requirement requirement : task.getRequirements()) {
            VerificationResult status = PASSED;
            for (Test test : requirement.getTests()) {
                ExecutionResult executionResult = codeRunner.run(code, test.getProgramInput());
                if (executionResult.hasCompilationFailed()) {
                    compilationExample = executionResult.getCompilation();
                    status = NOT_COMPILED;
                } else if (!executionResult.getProgramOutput().equals(test.getExpectedOutput())) {
                    status = FAILED;
                }
            }

            verificationResults.put(requirement.getName(), status);
        }

        return verificationResults.values().stream().allMatch(status -> status.equals(PASSED)) ?
                VerificationSummary.ok(verificationResults) :
                VerificationSummary.failure(verificationResults, compilationExample);
    }
}
