package com.school.domain.code.program;

import com.school.domain.code.program.compiler.CompilationResult;
import com.school.domain.code.program.compiler.JavaClassCompiler;
import com.school.domain.code.file.InputOutputHelper;
import com.school.domain.code.program.javaclass.JavaClassFactory;
import com.school.domain.code.program.javaclass.PotentialJavaClass;
import com.school.domain.code.program.javaclass.ValidJavaClass;
import com.school.domain.code.program.runner.JavaMainMethodRunner;

import static com.school.domain.code.program.ExecutionResult.withFailedCompilation;
import static com.school.domain.code.program.ExecutionResult.withPassedCompilation;

public class ProgramExecutor {
    private final JavaMainMethodRunner javaClassRunner;
    private final InputOutputHelper inputOutputHelper;
    private final JavaClassCompiler javaClassCompiler;
    private final JavaClassFactory javaClassFactory;


    public ProgramExecutor() {
        this.javaClassRunner = new JavaMainMethodRunner();
        this.inputOutputHelper = new InputOutputHelper();
        this.javaClassCompiler = new JavaClassCompiler();
        this.javaClassFactory = new JavaClassFactory();
    }

    public ExecutionResult execute(Program program) throws Exception {
        CompilationResult compilationResult = javaClassCompiler.compile(program.getPotentialClass());
        if (!compilationResult.isSuccessful()) {
            return withFailedCompilation(new Compilation(compilationResult.toProblems()));
        }

        inputOutputHelper.createInputOutputFiles(program);
        javaClassRunner.runMainMethod(toValidClass(program.getPotentialClass()));

        return withPassedCompilation(inputOutputHelper.readOutputContent(program.getEnvironment()));
    }

    private ValidJavaClass toValidClass(PotentialJavaClass potentialJavaClass) {
        return javaClassFactory.create(potentialJavaClass.getEnvironment(),
                potentialJavaClass.getCode());
    }
}
