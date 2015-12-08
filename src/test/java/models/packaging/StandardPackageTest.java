package models.packaging;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import utils.DummyPackage;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 30/11/15.
 */
public class StandardPackageTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Ignore
    public void testUnpack() throws Exception {
        TemporaryFolder tmp = new TemporaryFolder();
        tmp.create();
        Package testPackage = new DummyPackage("test");
        testPackage.setPath("packages");
        testPackage.setUnpackDirectory(tmp.getRoot().toString());
    }

    public void testStandardUnpacker() {
    }
}