package com.school.app.code;

import com.school.domain.code.JavaClassExecutor;
import com.school.domain.code.JavaClass;
import com.school.domain.code.Package;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.nio.file.Files.readAllBytes;

public class JavaClassExecutorTest {
    private static final String CLASS_NAME = "Test";

    private static final String FIRST_PACKAGE_PART = "com";
    private static final String SECOND_PACKAGE_PART = "school";
    private static final String CLASS_PACKAGE = FIRST_PACKAGE_PART + separator + SECOND_PACKAGE_PART;

    private static final String TEMPLATE_FULL_NAME = "Template.java";
    private static final String TEXT_FILE_FULL_NAME = "test.txt";

    private static final String ROOT_DIR = "C:\\Users\\Nikol\\IdeaProjects\\java-school\\src\\test\\resources\\code";
    private static final String TEMPLATE_DIR = ROOT_DIR + separator + "template";
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    private static final Path EXPECTED_JAVA_CLASS_PATH =
            Paths.get(ROOT_DIR + separator + CLASS_PACKAGE + separator + CLASS_NAME + ".class");
    private static final Path EXPECTED_CLASS_PATH =
            Paths.get(ROOT_DIR + separator + CLASS_PACKAGE + separator + CLASS_NAME + ".java");
    private static final Path EXPECTED_TEXT_FILE_PATH = Paths.get(TMP_DIR + TEXT_FILE_FULL_NAME);

    @After
    public void cleanUp() throws IOException {
        Files.deleteIfExists(EXPECTED_JAVA_CLASS_PATH);
        Files.deleteIfExists(EXPECTED_CLASS_PATH);
        Files.deleteIfExists(EXPECTED_TEXT_FILE_PATH);
    }

    @Test
    public void shouldExecutePassedClass() throws Exception {
        new JavaClassExecutor(ROOT_DIR).execute(aTemplateJavaClass());

        Assert.assertTrue(EXPECTED_JAVA_CLASS_PATH.toFile().exists());
        Assert.assertTrue(EXPECTED_CLASS_PATH.toFile().exists());
        Assert.assertTrue(EXPECTED_TEXT_FILE_PATH.toFile().exists());
    }

    @Test
    public void shouldFailOnCompilationWhenCannotCompile() throws Exception {
        new JavaClassExecutor(ROOT_DIR).execute(aNonCompilingClass());

        Assert.assertTrue(EXPECTED_CLASS_PATH.toFile().exists());
        Assert.assertFalse(EXPECTED_JAVA_CLASS_PATH.toFile().exists());
        Assert.assertFalse(EXPECTED_TEXT_FILE_PATH.toFile().exists());
    }

    private JavaClass aNonCompilingClass() {
        return new JavaClass(new Package(FIRST_PACKAGE_PART, SECOND_PACKAGE_PART), CLASS_NAME,
                "Something that will not compile");
    }

    private JavaClass aTemplateJavaClass() throws IOException {
        Path pathToTemplate = Paths.get(TEMPLATE_DIR + separator + TEMPLATE_FULL_NAME);

        return new JavaClass(new Package(FIRST_PACKAGE_PART, SECOND_PACKAGE_PART), CLASS_NAME,
                new String(readAllBytes(pathToTemplate)));
    }
}
