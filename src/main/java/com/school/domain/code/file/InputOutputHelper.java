package com.school.domain.code.file;

import com.school.domain.code.program.Environment;
import com.school.domain.code.program.Program;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputOutputHelper {

    public void createInputOutputFiles(Program program) throws Exception {
        String inputContent = program.getInput().isPresent() ? program.getInput().get() : "";
        aFileFactory().create(program.getEnvironment().fullPathToInput(), inputContent);

        aFileFactory().create(program.getEnvironment().fullPathToOutput(), "");
    }

    public String readOutputContent(Environment environment) throws Exception {
        byte[] content = Files.readAllBytes(Paths.get(environment.fullPathToOutput()));
        return new String(content);
    }

    private FileFactory aFileFactory() {
        return new FileFactory();
    }
}
