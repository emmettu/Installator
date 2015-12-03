import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstallerTest {

    @Test
    public void testMain() throws Exception {
        try {
            BuildInstaller installer = new BuildInstaller();
            installer.main(new String[]{});
        }
        catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}