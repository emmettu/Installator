package models.panels.securitydomain.ports;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 12/05/16.
 */
public class StandalonePortsTest {

    @Test
    public void testMake() throws Exception {
        List<Port> ports = new StandalonePorts().getPorts();

        for (Port p : ports) {
            System.out.println(p);
        }
    }

}