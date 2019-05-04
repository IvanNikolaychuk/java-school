package com.school.domain.code.task.verification;

import com.school.app.CodeRunner;
import com.school.domain.code.program.Compilation;
import com.school.domain.code.program.ExecutionResult;
import com.school.domain.code.task.Requirement;
import com.school.domain.code.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.school.domain.code.task.verification.VerificationStatus.FAILED;
import static com.school.domain.code.task.verification.VerificationStatus.NOT_COMPILED;
import static com.school.domain.code.task.verification.VerificationStatus.PASSED;

public class VerificationService {
    private final CodeRunner codeRunner = new CodeRunner();

    public VerificationResult verify(Task task, String code) throws Exception {
        Map<String, VerificationStatus> verificationResults = new HashMap<>();

        Compilation compilationExample = Compilation.noCompilationErrors();
        for (Requirement requirement : task.getRequirements()) {
            VerificationStatus status = PASSED;
            for (Specification specification : requirement.getSpecifications()) {
                ExecutionResult executionResult = codeRunner.run(code, specification.getProgramInput());
                if (executionResult.hasCompilationFailed()) {
                    compilationExample = executionResult.getCompilation();
                    status = NOT_COMPILED;
                } else if (!executionResult.getOutput().equals(specification.getExpectedOutput())) {
                    status = FAILED;
                }
            }

            verificationResults.put(requirement.getName(), status);
        }

        return verificationResults.values().stream().allMatch(status -> status.equals(PASSED)) ?
                VerificationResult.ok(verificationResults) :
                VerificationResult.failure(verificationResults, compilationExample);
    }
}
