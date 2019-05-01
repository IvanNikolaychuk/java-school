package com.school.domain.code.compiler;

import com.school.domain.code.file.FileFactory;
import com.school.domain.code.javaclass.Class;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.io.File.separator;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class JavaClassCompiler {
    private final FileFactory fileFactory;

    public JavaClassCompiler() {
        this.fileFactory = new FileFactory();
    }

    public CompilationResult compile(Class aClass) throws Exception {
        String fullPathToClass = aClass.getFullPathWithExtension(separator);

        fileFactory.create(fullPathToClass, aClass.getCode());

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        aCompilationTask(fullPathToClass, diagnostics).call();
        aFileManager().close();

        return new CompilationResult(diagnostics, aClass);
    }

    private CompilationTask aCompilationTask(String fullPathWithoutExtension,
                                             DiagnosticCollector<JavaFileObject> diagnostics) {
        return getSystemJavaCompiler().getTask(null,
                aFileManager(),
                diagnostics,
                null,
                null,
                aCompilationUnit(fullPathWithoutExtension));
    }

    private Iterable<? extends JavaFileObject> aCompilationUnit(String fullPathWithoutExtension) {
        File[] javaClassFile = {
                Paths.get(fullPathWithoutExtension).toFile(),
        };

        return aFileManager().getJavaFileObjectsFromFiles(Arrays.asList(javaClassFile));
    }

    private StandardJavaFileManager aFileManager() {
        return getSystemJavaCompiler().getStandardFileManager(null, null, null);
    }
}
