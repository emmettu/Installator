package controllers.textstream;

import models.unpacking.Unpacker;
import org.junit.Test;
import org.mockito.Mockito;
import utils.ConsoleTest;
import views.ui.textstream.ConsoleTextStream;
import views.ui.textstream.TextStream;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 15/12/15.
 */
public class UnpackerDisplayControllerTest extends ConsoleTest {

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

    private Unpacker getMockUnpacker() {
        Stack<Path> stack = new Stack<>();
        stack.add(Paths.get("test"));
        Unpacker mockUnpacker = Mockito.mock(Unpacker.class);
        Mockito.when(mockUnpacker.getUnpackedFiles()).thenReturn(stack);

        return mockUnpacker;
    }

}