package views.ui.textinput;

import controllers.Controller;
import controllers.textinput.PathInputController;
import org.junit.Before;
import org.junit.Test;
import utils.ConsoleTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 07/12/15.
 */
public class ConsoleTextInputFieldTest extends ConsoleTest {

    private ConsoleTextInputField field;

    @Before
    public void setUp() {
        field = new ConsoleTextInputField();
    }

    @Test
    public void testSetPrompt() throws Exception {
        field.setPrompt("whatever");
        field.display();
        assertEquals("whatever", outContent.toString());
    }

    @Test
    public void addController() throws Exception {
        //Controller controller = new PathInputController();
        //field.addController(controller);
    }

}