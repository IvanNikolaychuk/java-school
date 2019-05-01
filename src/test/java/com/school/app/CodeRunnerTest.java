package com.school.app;

import com.school.domain.code.program.ProgramExecutionResult;
import com.school.utils.RandomString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class CodeRunnerTest {
    private CodeRunner codeRunner = new CodeRunner(System.getProperty("java.io.tmpdir") + "academy");

    @Test
    public void shouldRunCodeWithoutInput() throws Exception {
        String text = "Hello";

        ProgramExecutionResult result = codeRunner.run(RandomString.generate(), codeWithoutScanner(text), "");

        Assert.assertEquals(result.getOutput(), text);
    }

    @Test
    public void shouldRunCodeWithInput() throws Exception {
        String text = "Hello";
        String input = "Ivan";

        ProgramExecutionResult result = codeRunner.run(RandomString.generate(), codeWithScanner(text), input);

        Assert.assertEquals(result.getOutput(), text + input);
    }

    @Test
    public void shouldRunCodeThatIsNotCompiling() throws Exception {
        String packageName = RandomString.generate();

        ProgramExecutionResult result = codeRunner.run(packageName, codeNotCompiling(packageName), "");

        Assert.assertEquals(result.getOutput(), "");
        Assert.assertEquals(result.getCompilation().getProblems(), Collections.singletonList(
                packageName + "\\Test.java:5: error: ';' expected\n" +
                        "\t\tSystem.out.print(\"Hello\")\n" +
                        "\t\t                         ^"
        ));
    }

    private String codeNotCompiling(String packageName) {
        return "package " + packageName + ";\n" +
                "\n" +
                "public class Test {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.print(\"Hello\")\n" +
                "\t}\n" +
                "}";
    }

    private String codeWithoutScanner(String text) {
        return "package " + RandomString.generate() + ";\n" +
                "\n" +
                "public class Test{\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.print(\"" + text + "\");\n" +
                "\t}\n" +
                "}";
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