package com.school.domain.code.program;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.school.domain.code.file.InputOutputHelper;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.javaparser.StaticJavaParser.parseStatement;

public class InputOutputStreamsDecorator {
    public String decorate(Environment environment, String code) {
        CompilationUnit compilationUnit = new JavaParser()
                .parse(code)
                .getResult()
                .get();

        for (Node node : compilationUnit.getType(0).getMembers()) {
            if (node instanceof MethodDeclaration && ((MethodDeclaration) node).getNameAsString().equals("main")) {
                MethodDeclaration mainMethod = (MethodDeclaration) node;
                Optional<BlockStmt> oldBlock = mainMethod.getBody();
                if (oldBlock.isPresent()) {
                    BlockStmt newBlock = decorate(environment, oldBlock.get());
                    mainMethod.replace(oldBlock.get(), newBlock);
                    mainMethod.addThrownException(Exception.class);
                    compilationUnit.addImport(PrintStream.class);
                    compilationUnit.addImport(File.class);
                    compilationUnit.addImport(InputStream.class);
                    compilationUnit.addImport(FileInputStream.class);
                }
            }

            return compilationUnit.toString();
        }

        return code;
    }

    private BlockStmt decorate(Environment environment, BlockStmt body) {
        InputOutputHelper inputOutputHelper = new InputOutputHelper();

        return new BlockStmt().addStatement(parseStatement(
                "try (\n" +
                        "PrintStream out = new PrintStream(new File(\"" + normalize(environment.fullPathToOutput()) + "\"));\n" +
                        "InputStream in = new FileInputStream(new File(\"" + normalize(environment.fullPathToInput()) + "\"))){\n" +
                        "System.setIn(in);\n" +
                        "System.setOut(out);\n" +
                        toString(body.getStatements()) +
                        "}"
        ));
    }

    private String normalize(String path) {
        return path.replace("\\", "\\\\");
    }

    private String toString(NodeList<Statement> statements) {
        List<String> strings = statements.stream().map(Node::toString).collect(Collectors.toList());
        return Strings.join(strings, '\n');
    }
}
