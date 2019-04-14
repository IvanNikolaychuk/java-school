package tmp;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import static java.io.File.separator;

public class Test {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get(System.getProperty("java.io.tmpdir") + separator + "test.txt");
        Files.write(path, "".getBytes());
    }
}
