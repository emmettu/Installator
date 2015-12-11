package utils;

import models.packaging.Package;
import models.packaging.StandardPackage;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Created by eunderhi on 30/11/15.
 * This package unpacks /test.jar in the resources folder.
 * It inherits from StandardPackage and does everything the same
 * except it overrides the getRunningJarLocation to grab the resources
 * jar instead of the currently running one.
 */
public class DummyPackage extends StandardPackage {


    public DummyPackage(String packageName) {
        super(packageName);
    }


   @Override
    public String getRunningJarLocation() {
       try {
           return getClass().getResource("/test.jar").toURI().getPath();
       }
       catch (URISyntaxException e) {
           return null;
       }
   }

}
