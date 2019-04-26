package com.school.domain.code;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;

import java.util.Optional;

public class JavaClassFactory {
    public JavaClass create(String content) {
        ParseResult<CompilationUnit> result = new JavaParser().parse(content);
        if (!result.getResult().isPresent()) throw new IllegalArgumentException("Can not parse java file");

        String packageName = extractPackageName(result.getResult().get());
        String className = extractClassName(result.getResult().get());

        return new JavaClass(aPackage(packageName), className, content);
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
