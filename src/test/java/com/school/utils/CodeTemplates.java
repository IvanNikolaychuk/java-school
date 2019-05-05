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

    public static String codeWithMaxMethod() {
        return "package " + RandomString.generate() + ";\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) { }\n" +
                "    \n" +
                "    public static int max(int a, int b) {\n" +
                "        return a > b ? a : b;\n" +
                "    }\n" +
                "}";
    }
}
