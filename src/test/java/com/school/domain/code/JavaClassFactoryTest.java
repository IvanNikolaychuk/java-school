package com.school.domain.code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JavaClassFactoryTest {
    private static final String CLASS_NAME = "Main";
    private static final String CLASS_PACKAGE = "com.school";

    private String classContent;

    public JavaClassFactoryTest(String classContent) {
        this.classContent = classContent;
    }

    @Test
    public void shouldCreateCorrectJavaClassRepresentation() {
        JavaClassFactory factory = new JavaClassFactory();

        JavaClass result = factory.from(classContent);

        assertEquals(result.getPackages("."), CLASS_PACKAGE);
        assertEquals(result.getName(), CLASS_NAME);
    }


    @Parameterized.Parameters
    public static Collection<Object[]> classDefinitions() {
        return Arrays.asList(new Object[][] {
                {"package " + CLASS_PACKAGE + ";\n public class " + CLASS_NAME + " {}"},
                {"package " + CLASS_PACKAGE + ";\n public class \n" + CLASS_NAME + "\n {}"},
                {"package " + CLASS_PACKAGE + ";public class " + CLASS_NAME + "{}"},
        });
    }
}