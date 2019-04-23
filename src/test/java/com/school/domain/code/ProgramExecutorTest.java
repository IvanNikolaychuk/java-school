package com.school.domain.code;

import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

import static java.io.File.separator;
import static java.nio.file.Files.readAllLines;
import static org.junit.Assert.*;

public class ProgramExecutorTest {
    private final static String INPUT = "Some input";
    private final static String CLASS_PACKAGE = UUID.randomUUID().toString();
    private static final String ROOT_DIR_PATH = System.getProperty("java.io.tmpdir") + "academy";

    @Test
    public void shouldCreateFileWhenProgramHasInput() throws Exception {
        Program program = aProgramWithInput();
        String expectedFilePath = ROOT_DIR_PATH + separator  + CLASS_PACKAGE + separator + "input.txt";

        new ProgramExecutor(ROOT_DIR_PATH).execute(program);

        Path path = Paths.get(expectedFilePath);
        assertEquals(readAllLines(path), ImmutableList.of(INPUT));
        path.toFile().delete();
    }

    private Program aProgramWithInput() {

        JavaClass javaClass = new JavaClass(new Package(CLASS_PACKAGE), "Test", "");
        return new Program(javaClass, INPUT, ROOT_DIR_PATH);
    }
}