package com.school.domain.code.task.verification;

import com.school.domain.code.task.Requirement;
import com.school.domain.code.task.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Sets.newHashSet;
import static com.school.utils.CodeTemplates.codeNotCompiling;
import static com.school.utils.CodeTemplates.codePrinting;

public class VerificationServiceTest {
    private static final String TASK_ID = "taskId";

    @Test
    public void shouldVerifyProgramThatPasses() throws Exception {
        String expectedOutput = "Hello world";
        Requirement requirement = new Requirement(TASK_ID, "Should print Hello World", of(shouldPrintSpecification(expectedOutput)));
        Task task = new Task("Title", newHashSet(requirement));

        VerificationResult result = new VerificationService().verify(task, codePrinting(expectedOutput));
        Assert.assertTrue(result.isPassed());
        Assert.assertNull(result.getExecutionResult());
    }

    @Test
    public void shouldVerifyProgramThatFails() throws Exception {
        Requirement requirement = new Requirement(TASK_ID, "Should print Hello World", Arrays.asList(shouldPrintSpecification("Hello world")));

        Task task = new Task("Title", newHashSet(requirement));

        VerificationResult result = new VerificationService().verify(task, codePrinting("Some other text"));
        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
    }

    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        Requirement requirement = new Requirement(TASK_ID, "Should print Hello World", Arrays.asList(shouldPrintSpecification("Hello world")));
        Task task = new Task("Title", newHashSet(requirement));

        VerificationResult result = new VerificationService().verify(task, codeNotCompiling());

        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
        Assert.assertFalse(result.getExecutionResult().getCompilation().getProblems().isEmpty());
    }

    private Specification shouldPrintSpecification(String expectedOutput) {
        return new Specification("requirementId", "", expectedOutput);
    }
}