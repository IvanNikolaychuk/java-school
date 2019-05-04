package com.school.domain.code.task.verification;

import com.school.domain.code.task.Task;
import org.junit.Assert;

import static com.google.common.collect.Sets.newHashSet;
import static com.school.domain.code.task.verification.VerificationResult.*;
import static com.school.utils.CodeTemplates.codeNotCompiling;
import static com.school.utils.CodeTemplates.codePrinting;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class VerificationServiceTest {

    @org.junit.Test
    public void shouldVerifyProgramThatPasses() throws Exception {
        Requirement requirement = shouldPrintText("Hello world");
        Task task = new Task("Title", newHashSet(requirement));

        VerificationSummary result = new VerificationService().verify(task, codePrinting("Hello world"));

        Assert.assertTrue(result.isPassed());
        Assert.assertNotNull(result.getCompilation());
        Assert.assertTrue(result.getCompilation().getProblems().isEmpty());
        assertEquals(result.getResults().get(requirement.getName()), PASSED);
    }

    @org.junit.Test
    public void shouldVerifyProgramThatFails() throws Exception {
        Requirement printRequirement = shouldPrintText("Hello World");
        Requirement noRequirement = new Requirement("No Requirement");
        Task task = new Task("Title", newHashSet(printRequirement, noRequirement));

        VerificationSummary result = new VerificationService().verify(task, codePrinting("Some other text"));

        assertFalse(result.isPassed());
        Assert.assertTrue(result.getCompilation().getProblems().isEmpty());
        assertEquals(result.getResults().get(printRequirement.getName()), FAILED);
        assertEquals(result.getResults().get(noRequirement.getName()), PASSED);
    }

    @org.junit.Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        Requirement requirement = shouldPrintText("Hello World");
        Task task = new Task("Title", newHashSet(requirement));

        VerificationSummary result = new VerificationService().verify(task, codeNotCompiling());

        assertFalse(result.isPassed());
        assertFalse(result.getCompilation().getProblems().isEmpty());
        assertEquals(result.getResults().get(requirement.getName()), NOT_COMPILED);
    }

    private Requirement shouldPrintText(String text) {
        Test test = new Test("requirementId", "", text);
        return new Requirement("Should print text", test);
    }
}