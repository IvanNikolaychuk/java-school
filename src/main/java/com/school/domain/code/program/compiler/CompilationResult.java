package com.school.domain.code.program.compiler;

import com.school.domain.code.program.javaclass.Class;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CompilationResult {
    private final DiagnosticCollector<JavaFileObject> diagnostics;
    private final Class aClass;

    CompilationResult(DiagnosticCollector<JavaFileObject> diagnostics, Class aClass) {
        this.diagnostics = diagnostics;
        this.aClass = aClass;
    }

    public boolean isSuccessful() {
        return diagnostics.getDiagnostics().isEmpty();
    }

    public List<String> toProblems() {
        return diagnostics.getDiagnostics()
                .stream()
                .map(diagnostic -> diagnostic.toString().replace(aClass.getEnvironment().getRootDir() + File.separator, ""))
                .collect(Collectors.toList());
    }
}
