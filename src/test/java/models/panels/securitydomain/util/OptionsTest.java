package models.panels.securitydomain.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 09/05/16.
 */
public class OptionsTest {

    @Test
    public void testFromString() throws Exception {
        Map<String, String> actualMap = Options.fromString("test1=test2,test2=test3,test4=\"test5=,test6\"");
        Map<String, String> expectedMap = new HashMap<>();

        expectedMap.put("test1", "test2");
        expectedMap.put("test2", "test3");
        expectedMap.put("test4", "test5=,test6");

        assertEquals("Options were parsed from string incorrectly", expectedMap, actualMap);
    }

}