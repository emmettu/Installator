package utils;

import models.packaging.Package;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Created by eunderhi on 30/11/15.
 * This package unpacks /InstallUtils-0.1.jar in the resources folder.
 * It's entirely for testing purposes
 */
public class DummyPackage extends Package {


    private void unpackJar() throws IOException {
        JarFile file = new JarFile(getClass().getResource("/InstallUtils-0.1.jar").getPath());
        JarEntry jarEntry = file.getJarEntry(getPath());
        writeEntryContentsToFile(file, jarEntry);
    }

    private void writeEntryContentsToFile(JarFile file, JarEntry entry) {
        try {
            InputStream is = file.getInputStream(entry);
            FileOutputStream fos = new FileOutputStream(getUnpackDirectory() + "/wildlfy");
            while(is.available() > 0) {
                fos.write(is.read());
            }
            fos.close();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyListeners(Object message) {

    }
}
