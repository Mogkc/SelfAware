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
    public void occurencesTest() throws Exception {
        SelfAware a = new SelfAware();
        try {
            a.occurrences("invalid");
        } catch(Exception e) {
            assertEquals(true, true);
        }
        assertEquals(25, a.occurrences(code) );
    }

    @Test
    public void appendTest() throws IOException {
        SelfAware b = new SelfAware();
        b.append(code,"\n//Visual check" );
    }
}