package com.school.domain.code.task.verification;

import com.school.app.CodeRunner;
import com.school.domain.code.program.ExecutionResult;
import com.school.domain.code.task.Task;

public class VerificationService {
    private final CodeRunner codeRunner = new CodeRunner();

    public VerificationResult verify(Task task, String code) throws Exception {
        for (Specification specification : task.getSpecifications()) {
            String programInput = specification.getProgramInput();
            String expectedOutput = specification.getExpectedOutput();

            ExecutionResult executionResult = codeRunner.run(code, programInput);
            if (!executionResult.getOutput().equals(expectedOutput)) {
                return VerificationResult.failure(executionResult, programInput, expectedOutput);
            }
        }

        return VerificationResult.ok();
    }
}
