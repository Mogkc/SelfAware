import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class SelfAware implements edu.gcccd.csis.Language {
    private HashMap<Integer, HashMap> reserv;

    public int occurrences(String sourceFile) throws Exception {
        int occur = 0;
        sorter();
        Scanner sa = new Scanner(new File(sourceFile) ).useDelimiter(
                "\\s+|\\W");
        while (sa.hasNext()) {
            if (matches(sa.next()))
                occur++;
        }
        sa.close();
        return occur;
    }

    /*Sees if the length is keyed to a hashmap,
    * if so checks words of that length for equality*/
    private boolean matches(String in) {
        HashMap<Integer, String> search = reserv.get((Integer) in.length());
        if (search == null)
            return false;
        for (Integer x = 1; x < search.size(); x++) {
            if (search.get(x).equals(in))
                return true;
        }
        return false;
    }

    /*Places each reserved word in a hashmap of other words with its length,
    * and keeps all of those hashmaps with the key to it being the length of words inside*/
    void sorter() {
        reserv = new HashMap<Integer, HashMap>();
        for (String x : ReservedWords) {
            Integer temp = x.length();

            if(reserv.get(temp) != null) {
                reserv.get(temp).put((Integer) reserv.get(temp).size(), x);
            } else {
                HashMap<Integer, String> created = new HashMap<Integer, String>();
                created.put(0, x);
                reserv.put(temp, created);
            }
        }
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
