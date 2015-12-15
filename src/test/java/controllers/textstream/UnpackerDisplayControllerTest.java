package controllers.textstream;

import models.packaging.StandardPackage;
import models.unpacking.Unpacker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.DummyPackage;
import views.ui.textstream.ConsoleTextStream;
import views.ui.textstream.TextStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 15/12/15.
 */
public class UnpackerDisplayControllerTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPerformAction() throws Exception {
        Unpacker unpacker = getMockUnpacker();
        TextStream stream = new ConsoleTextStream();
        UnpackerDisplayController udc = new UnpackerDisplayController();

        udc.setUnpacker(unpacker);
        udc.setTextStream(stream);

        udc.performAction();
        assertEquals(outContent.toString(), "test\n");
    }

    Unpacker getMockUnpacker() {
        Stack<Path> stack = new Stack<>();
        stack.add(Paths.get("test"));
        Unpacker mockUnpacker = Mockito.mock(Unpacker.class);
        Mockito.when(mockUnpacker.getUnpackedFiles()).thenReturn(stack);

        return mockUnpacker;
    }

}