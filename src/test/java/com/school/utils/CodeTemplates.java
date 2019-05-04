package com.school.utils;

public class CodeTemplates {
    public static String codePrinting(String text) {
        return "package " + RandomString.generate() + ";\n" +
                "\n" +
                "public class Test{\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.print(\"" + text + "\");\n" +
                "\t}\n" +
                "}";
    }

    public static String codeNotCompiling() {
        return "package " + RandomString.generate() + ";\n" +
                "\n" +
                "public class Test {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.print(\"Hello\")\n" +
                "\t}\n" +
                "}";
    }
}
