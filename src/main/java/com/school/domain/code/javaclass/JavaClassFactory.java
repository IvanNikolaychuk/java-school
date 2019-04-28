package com.school.domain.code.javaclass;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.school.domain.code.program.InputOutputStreamsDecorator;

import java.util.Optional;

public class JavaClassFactory {
    private final InputOutputStreamsDecorator inputOutputStreamsDecorator;

    public JavaClassFactory() {
        inputOutputStreamsDecorator = new InputOutputStreamsDecorator();
    }

    public JavaClass create(String rootDir, String taskId, String code) {
        ParseResult<CompilationUnit> result = new JavaParser().parse(code);
        if (!result.getResult().isPresent()) throw new IllegalArgumentException("Can not parse java file");

        String packageName = extractPackageName(result.getResult().get());
        String className = extractClassName(result.getResult().get());

        String decoratedCode = inputOutputStreamsDecorator.decorate(rootDir, taskId, code);

        return JavaClass.JavaClassBuilder.aJavaClass()
                .withRootDir(rootDir)
                .withTaskId(taskId)
                .withClassPackage(aPackage(packageName))
                .withName(className)
                .withCode(decoratedCode)
                .build();
    }

    private Package aPackage(String packageName) {
        return new Package(packageName.split("\\."));
    }

    private String extractClassName(CompilationUnit compilationUnit) {
        return compilationUnit.getTypes().stream()
                .map(type -> type.getName().asString())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private String extractPackageName(CompilationUnit compilationUnit) {
        Optional<PackageDeclaration> packageDeclaration = compilationUnit.getPackageDeclaration();
        if (!packageDeclaration.isPresent()) throw new IllegalArgumentException("Can not find package declaration");
        return packageDeclaration.get().getName().asString();
    }

}
