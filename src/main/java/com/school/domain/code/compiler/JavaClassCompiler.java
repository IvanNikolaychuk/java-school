package com.school.domain.code.compiler;

import com.school.domain.code.JavaClass;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class JavaClassCompiler {
    private final String path;

    public JavaClassCompiler(String path) {
        this.path = path;
    }

    public CompilationResult compile(JavaClass javaClass) throws IOException {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        aCompilationTask(javaClass, diagnostics).call();
        aFileManager().close();

        return new CompilationResult(diagnostics);
    }

    private CompilationTask aCompilationTask(JavaClass javaClass, DiagnosticCollector<JavaFileObject> diagnostics) {
        return getSystemJavaCompiler().getTask(null,
                aFileManager(),
                diagnostics,
                null,
                null,
                aCompilationUnit(javaClass));
    }

    private Iterable<? extends JavaFileObject> aCompilationUnit(JavaClass javaClass) {
        File[] javaClassFile = {Paths.get(this.path, javaClass.getNameWithExtension()).toFile()};

        return aFileManager().getJavaFileObjectsFromFiles(Arrays.asList(javaClassFile));
    }

    private StandardJavaFileManager aFileManager() {
        return getSystemJavaCompiler().getStandardFileManager(null, null, null);
    }
}
