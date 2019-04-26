package com.school.domain.code;

public class ProgramFactory {
    private final JavaClassFactory javaClassFactory;
    private final InputOutputStreamsDecorator inputOutputStreamsDecorator;

    public ProgramFactory() {
        this.javaClassFactory = new JavaClassFactory();
        this.inputOutputStreamsDecorator = new InputOutputStreamsDecorator();
    }

    public Program create(String content, String input, String rootDir) {
        JavaClass javaClass = javaClassFactory.create(content);
        Program program = new Program(javaClass, input, rootDir);

        return inputOutputStreamsDecorator.decorateWithInputOutput(program);
    }
}
