package views.ui.checkbox;

import org.junit.Before;
import org.junit.Test;
import utils.ConsoleTest;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 08/01/16.
 */
public class ConsoleCheckBoxTest extends ConsoleTest {

    ConsoleCheckBox box;

    @Before
    public void setUpCheckBox() {
        box = new ConsoleCheckBox("test");
    }

    @Test
    public void testUncheckedDisplay() throws Exception {
        box.display();
        assertEquals(outContent.toString(), "test\nEnter 1 to select: [ ] ");
    }

    @Test
    public void testCheckedDisplay() throws Exception {
        box.setChecked(true);
        box.display();
        assertEquals(outContent.toString(), "test\nEnter 1 to select: [X] ");
    }
}