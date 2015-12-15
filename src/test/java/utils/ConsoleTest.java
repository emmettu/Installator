package utils;

import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by eunderhi on 15/12/15.
 * Base class for console related tests. Sets up output streams
 * so that console elements that print to screen can be verified.
 */
public class ConsoleTest {

    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
}
