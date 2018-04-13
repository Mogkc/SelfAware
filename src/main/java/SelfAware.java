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
        edu.gcccd.csis.Language.sort();
        Scanner sa = new Scanner(new File(sourceFile) ).useDelimiter(
                "\\s+|\\W");
        while (sa.hasNext()) {
            String temp = sa.next();
            int j = firstOfLength(temp.length());
            while(j>-1 && j<ReservedWords.length) {
                if (ReservedWords[j].equals(temp)) {
                    occur++;
                } else if (ReservedWords[j].length() != temp.length())
                    break;
                j++;
            }
        }
        sa.close();
        return occur;
    }

    private int firstOfLength(int length) {
        int min = 0, max = ReservedWords.length-1;
        while (min <= max) {
            int mid = (max+min)/2;
            //If the right length is found, iterate back to the first then return it.
            if (ReservedWords[mid].length() == length) {
                return first(mid);
            }
            //Otherwise, adjust the boundaries, remembering longest first
            if (ReservedWords[mid].length() > length) {
                min = mid +1;
            } else max = mid -1;
        }
        //If there are none of the specified length,
        return -1;
    }

    private int first(int place) {
        int length = ReservedWords[place].length();
        //Decrease  place to the first reserved word of that length
        while (true) {
            if (place==0 || ReservedWords[place-1].length() != length)
                break;
            place--;
        }
        return place;
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
