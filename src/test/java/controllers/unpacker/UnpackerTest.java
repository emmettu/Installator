package controllers.unpacker;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import utils.DummyPackage;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by eunderhi on 10/12/15.
 */
public class UnpackerTest {

    TemporaryFolder folder;
    DummyPackage dummyPack;
    Unpacker unpacker;

    @Before
    public void setUp() throws Exception {
        folder = new TemporaryFolder();
        folder.create();
        dummyPack = new DummyPackage("test3");
        dummyPack.setUnpackDirectory(folder.getRoot().toString());
        unpacker = new Unpacker(dummyPack);

    }

    @Test
    public void testRegularUnpack() throws Exception {
        unpacker.unpack();
        File[] unpackedFiles = folder.getRoot().listFiles();
        File unpackedFile = unpackedFiles[0];

        assertEquals(1, folder.getRoot().listFiles().length);
        assertEquals("test3", unpackedFile.getName());

        assertEquals(true, fileExists("/test3/test4"));
    }

    @Test
    public void testExcludeUnpack() throws Exception {
        dummyPack.addExclude("test5");
        unpacker.unpack();

        assertEquals(true, fileExists("/test3/test4"));
        assertEquals(true, fileExists("/test3/test6"));
        assertEquals(false, fileExists("/test3/test5"));
    }

    private boolean fileExists(String pathName) {
        return new File(folder.getRoot().toString() + pathName).exists();
    }

}