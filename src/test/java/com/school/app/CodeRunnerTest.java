package com.school.app;

import com.school.domain.code.program.ExecutionResult;
import com.school.domain.code.program.Method;
import com.school.utils.RandomString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static com.school.utils.CodeTemplates.codeNotCompiling;
import static com.school.utils.CodeTemplates.codePrinting;
import static com.school.utils.CodeTemplates.codeWithMaxMethod;
import static org.junit.Assert.assertEquals;

public class CodeRunnerTest {
    private CodeRunner codeRunner = new CodeRunner();

    @Test
    public void shouldRunCodeWithoutInput() throws Exception {
        String text = "Hello";

        ExecutionResult result = codeRunner.run(codePrinting(text), "");

        assertEquals(result.getProgramOutput(), text);
    }

    @Test
    public void shouldRunCodeWithInput() throws Exception {
        String text = "Hello";
        String input = "Ivan";

        ExecutionResult result = codeRunner.run(codeWithScanner(text), input);

        assertEquals(result.getProgramOutput(), text + input);
    }

    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        ExecutionResult result = codeRunner.run(codeNotCompiling(), "");

        assertEquals(result.getProgramOutput(), "");
        assertEquals(result.getCompilation().getProblems(), Collections.singletonList(
                "Test.java:5: error: ';' expected\n" +
                        "\t\tSystem.out.print(\"Hello\")\n" +
                        "\t\t                         ^"
        ));
    }

    @Test
    public void shouldRunCustomMethod() throws Exception {
        Class[] parameterTypes = new Class[] {int.class, int.class};
        Object[] args = new Object[] {1,2};
        ExecutionResult result = codeRunner.run(codeWithMaxMethod(),
                new Method("max", parameterTypes, args), "");

        assertEquals(result.getMethodResult(), 2);
    }

    private String codeWithScanner(String text) {
        return "package " + RandomString.generate() + ";\n" +
                "\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.print(\"" + text + "\" + new Scanner(System.in).next());\n" +
                "    }\n" +
                "}";
    }
}