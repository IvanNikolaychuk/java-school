package com.school.app;

import com.school.domain.code.program.ExecutionResult;
import com.school.utils.RandomString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static com.school.utils.CodeTemplates.codeNotCompiling;
import static com.school.utils.CodeTemplates.codePrinting;

public class CodeRunnerTest {
    private CodeRunner codeRunner = new CodeRunner();

    @Test
    public void shouldRunCodeWithoutInput() throws Exception {
        String text = "Hello";

        ExecutionResult result = codeRunner.run(codePrinting(text), "");

        Assert.assertEquals(result.getOutput(), text);
    }

    @Test
    public void shouldRunCodeWithInput() throws Exception {
        String text = "Hello";
        String input = "Ivan";

        ExecutionResult result = codeRunner.run(codeWithScanner(text), input);

        Assert.assertEquals(result.getOutput(), text + input);
    }

    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        ExecutionResult result = codeRunner.run(codeNotCompiling(), "");

        Assert.assertEquals(result.getOutput(), "");
        Assert.assertEquals(result.getCompilation().getProblems(), Collections.singletonList(
                "Test.java:5: error: ';' expected\n" +
                        "\t\tSystem.out.print(\"Hello\")\n" +
                        "\t\t                         ^"
        ));
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