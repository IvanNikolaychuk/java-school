package com.school.domain.code.compiler;

import com.school.domain.code.file.FileFactory;
import com.school.domain.code.javaclass.JavaClass;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.io.File.separator;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class JavaClassCompiler {
    private final FileFactory fileFactory;

    public JavaClassCompiler() {
        this.fileFactory = new FileFactory();
    }

    public CompilationResult compile(JavaClass javaClass) throws Exception {
        createFile(javaClass);
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
        File[] javaClassFile = {
                Paths.get(javaClass.getFullPathWithExtension(separator)).toFile(),
        };

        return aFileManager().getJavaFileObjectsFromFiles(Arrays.asList(javaClassFile));
    }

    private StandardJavaFileManager aFileManager() {
        return getSystemJavaCompiler().getStandardFileManager(null, null, null);
    }

    private void createFile(JavaClass javaClass) throws Exception {
        fileFactory.create(javaClass.getFullPathWithExtension(separator), javaClass.getContent());
    }
}
