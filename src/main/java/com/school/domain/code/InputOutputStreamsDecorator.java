package com.school.domain.code;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.javaparser.StaticJavaParser.parseStatement;

class InputOutputStreamsDecorator {
    Program decorateWithInputOutput(Program program) {
        CompilationUnit compilationUnit = new JavaParser()
                .parse(program.getJavaClass().getContent())
                .getResult()
                .get();

        for (Node node : compilationUnit.getType(0).getMembers()) {
            if (node instanceof MethodDeclaration && ((MethodDeclaration) node).getNameAsString().equals("main")) {
                MethodDeclaration mainMethod = (MethodDeclaration) node;
                Optional<BlockStmt> oldBlock = mainMethod.getBody();
                if (oldBlock.isPresent()) {
                    BlockStmt newBlock = decorate(program, oldBlock.get());
                    mainMethod.replace(oldBlock.get(), newBlock);
                    mainMethod.addThrownException(Exception.class);
                    compilationUnit.addImport(PrintStream.class);
                    compilationUnit.addImport(File.class);
                    compilationUnit.addImport(InputStream.class);
                    compilationUnit.addImport(FileInputStream.class);
                }
            }
            JavaClass javaClass = program.getJavaClass().withContent(compilationUnit.toString());
            return program.withJavaClass(javaClass);
        }

        return program;

    }

    private BlockStmt decorate(Program program, BlockStmt body) {
        return new BlockStmt().addStatement(parseStatement(
                "try (\n" +
                        "PrintStream out = new PrintStream(new File(\"" + normalize(program.fullPathToOutput()) + "\"));\n" +
                        "InputStream in = new FileInputStream(new File(\"" + normalize(program.fullPathToInput()) + "\"))){\n" +
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
