import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SelfAwareTest {
    final String code = System.getProperty("user.dir") + File.separator +
            "src" + File.separator + "test"
            + File.separator + "java" + File.separator
            + SelfAwareTest.class.getName().replace(".", File.separator) + ".java";

    @Test
    public void occurencesTest() throws IOException {
        SelfAware a = new SelfAware();
        try {
            a.occurrences("invalid");
        } catch(Exception e) {
            assertEquals(true, true);
        }
        try {
            assertEquals(25, a.occurrences(code) );
        } catch (Exception e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void appendTest() throws IOException {
        SelfAware b = new SelfAware();
        b.append(code,"\n//Visual check" );
    }
}
//Visual check