package com.school.domain.code.task.verification;

import com.google.common.collect.Sets;
import com.school.domain.code.task.Task;
import org.junit.Assert;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static com.school.utils.CodeTemplates.codeNotCompiling;
import static com.school.utils.CodeTemplates.codePrinting;

public class VerificationServiceTest {
    private static final String TASK_ID = "taskId";

    @Test
    public void shouldVerifyProgramThatPasses() throws Exception {
        String expectedOutput = "Hello world";

        Task task = new Task(TASK_ID,
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification(expectedOutput)));

        VerificationResult result = new VerificationService().verify(task, codePrinting(expectedOutput));
        Assert.assertTrue(result.isPassed());
        Assert.assertNull(result.getExecutionResult());
    }

    @Test
    public void shouldVerifyProgramThatFails() throws Exception {
        Task task = new Task(TASK_ID,
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification("Hello world")));

        VerificationResult result = new VerificationService().verify(task, codePrinting("Some other text"));
        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
    }

    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        Task task = new Task(TASK_ID,
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification("Hello World")));

        VerificationResult result = new VerificationService().verify(task, codeNotCompiling());

        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
        Assert.assertFalse(result.getExecutionResult().getCompilation().getProblems().isEmpty());
    }

    private Specification shouldPrintSpecification(String expectedOutput) {
        return new Specification(TASK_ID, "conditionId", "", expectedOutput);
    }
}