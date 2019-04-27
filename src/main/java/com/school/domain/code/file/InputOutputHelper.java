package com.school.domain.code.file;

import com.school.domain.code.program.Program;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputOutputHelper {
    private final String rootDir;

    public InputOutputHelper(String rootDir) {
        this.rootDir = rootDir;
    }

    public void createInputFile(Program program) throws Exception {
        String inputContent = program.getInput().isPresent() ? program.getInput().get() : "";
        aFileFactory().create(fullPathToInput(program.getTaskId()), inputContent);
    }

    public void createOutputFile(Program program) throws Exception {
        aFileFactory().create(fullPathToOutput(program.getTaskId()), "");
    }

    public String readOutputContent(Program program) throws Exception {
        byte[] content = Files.readAllBytes(Paths.get(fullPathToOutput(program.getTaskId())));
        return new String(content);
    }


    public String fullPathToInput(String taskId) {
        return rootDir + File.separator + taskId + File.separator + "input.txt";
    }

    public String fullPathToOutput(String taskId) {
        return rootDir + File.separator + taskId + File.separator + "output.txt";
    }

    private FileFactory aFileFactory() {
        return new FileFactory();
    }

}
