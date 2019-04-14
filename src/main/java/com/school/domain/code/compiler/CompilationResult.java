package com.school.domain.code.compiler;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class CompilationResult {
    private final DiagnosticCollector<JavaFileObject> diagnostics;

    public CompilationResult(DiagnosticCollector<JavaFileObject> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public boolean isSuccessful() {
        return diagnostics.getDiagnostics().isEmpty();
    }

    public String details() {
        String result = "TODO: implement normal description";

        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            return result;
        }
        return result;
    }

}
