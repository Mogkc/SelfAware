import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SelfAware implements edu.gcccd.csis.Language {

    public int occurrences(String sourceFile) throws Exception {
        int occur = 0;
        Scanner sa = new Scanner(new File(sourceFile) ).useDelimiter(
                "\\s+|\\W");
        while (sa.hasNext()) {
            String temp = sa.next();
            System.out.println(temp);
            for (String x : ReservedWords) {
                if (x.equals(temp)) {
                    occur++;
                }
            }
        }
        sa.close();
        return occur;
    }

    public void append(String sourceFile, String message) throws IOException {
        final Path source = Paths.get(sourceFile);
        Files.write(source, message.getBytes(), StandardOpenOption.APPEND);
    }

    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        sa.append(code,"\n//Keyword occurrences: " + sa.occurrences(code));
    }
}
