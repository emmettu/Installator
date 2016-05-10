package models.panels.securitydomain.ports;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 10/05/16.
 */
public class PortTest {

    @Test
    public void testGetValue() throws Exception {
        Port port = Port.create().withValue(100);
        assertEquals(
                "Port value not correct",
                100,
                port.getValue()
        );
    }

    @Test
    public void testWithOffset() throws Exception {
        Port port = Port.create().withValue(100);
        assertEquals(
                "Port value with offset not correct",
                150,
                port.withOffset(50).getValue()
        );
    }

    @Test
    public void testToString() throws Exception {
        Port port = Port.create().withValue(100).withName("test.port");
        assertEquals(
                "Port to string did not return the correct value",
                "${test.port:100}",
                port.toString()
        );
        assertEquals(
                "Port to string with offset did not return the correct value",
                "${test.port:150}",
                port.withOffset(50).toString()
        );
    }

}