package com.school.domain.code.task.verification;

import com.google.common.collect.Sets;
import com.school.domain.code.task.Class;
import com.school.domain.code.task.Task;
import com.school.utils.CodeTemplates;
import org.junit.Assert;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.*;

public class VerificationServiceTest {
    private static final String TASK_ID = "taskId";

    @Test
    public void shouldVerifyProgramThatPasses() throws Exception {
        String expectedOutput = "Hello world";
        Class aClass = aClass(CodeTemplates.codePrinting(expectedOutput));

        Task task = new Task(TASK_ID,
                of(aClass),
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification(expectedOutput)));

        VerificationResult result = new VerificationService().verify(task, aClass.getCode());
        Assert.assertTrue(result.isPassed());
        Assert.assertNull(result.getExecutionResult());
    }

    @Test
    public void shouldVerifyProgramThatFails() throws Exception {
        String expectedOutput = "Hello world";
        Class aClass = aClass(CodeTemplates.codePrinting("Some other text"));

        Task task = new Task(TASK_ID,
                of(aClass),
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification(expectedOutput)));

        VerificationResult result = new VerificationService().verify(task, aClass.getCode());
        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
    }


    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        Class aClass = aClass(CodeTemplates.codeNotCompiling());

        Task task = new Task(TASK_ID,
                of(aClass),
                of("Should print Hello World"),
                Sets.newHashSet(shouldPrintSpecification("Hello World")));

        VerificationResult result = new VerificationService().verify(task, aClass.getCode());
        Assert.assertFalse(result.isPassed());
        Assert.assertNotNull(result.getExecutionResult());
        Assert.assertFalse(result.getExecutionResult().getCompilation().getProblems().isEmpty());
    }

    private Specification shouldPrintSpecification(String expectedOutput) {
        return new Specification(TASK_ID, "conditionId", "", expectedOutput);
    }

    private Class aClass(String code) {
        return new Class(code, TASK_ID, "Main");
    }
}