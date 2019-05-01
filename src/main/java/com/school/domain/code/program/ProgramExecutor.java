package com.school.domain.code.program;

import com.school.domain.code.compiler.CompilationResult;
import com.school.domain.code.compiler.JavaClassCompiler;
import com.school.domain.code.file.InputOutputHelper;
import com.school.domain.code.javaclass.JavaClassFactory;
import com.school.domain.code.javaclass.PotentialJavaClass;
import com.school.domain.code.javaclass.ValidJavaClass;
import com.school.domain.code.runner.JavaMainMethodRunner;

import static com.school.domain.code.program.ProgramExecutionResult.withFailedCompilation;
import static com.school.domain.code.program.ProgramExecutionResult.withPassedCompilation;

public class ProgramExecutor {
    private final JavaMainMethodRunner javaClassRunner;
    private final InputOutputHelper inputOutputHelper;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaClassFactory javaClassFactory;


    public ProgramExecutor(String rootDirPath) {
        this.javaClassRunner = new JavaMainMethodRunner();
        this.inputOutputHelper = new InputOutputHelper(rootDirPath);
        this.javaClassCompiler = new JavaClassCompiler();
        this.javaClassFactory = new JavaClassFactory();
    }

    public ProgramExecutionResult execute(Program program) throws Exception {
        CompilationResult compilationResult = javaClassCompiler.compile(program.getPotentialClass());
        if (!compilationResult.isSuccessful()) {
            return withFailedCompilation(new Compilation(compilationResult.toProblems()));
        }

        inputOutputHelper.createInputOutputFiles(program);
        javaClassRunner.runMainMethod(toValidClass(program.getPotentialClass()));

        return withPassedCompilation(inputOutputHelper.readOutputContent(program));
    }

    private ValidJavaClass toValidClass(PotentialJavaClass potentialJavaClass) {
        return javaClassFactory.create(potentialJavaClass.getRootDir(),
                potentialJavaClass.getTaskId(),
                potentialJavaClass.getCode());
    }
}
