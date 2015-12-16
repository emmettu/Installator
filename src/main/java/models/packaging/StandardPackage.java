package models.packaging;

import models.packaging.utils.SizeFileWalker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 30/11/15.
 * This is the base implementation of the package class. It stores information about where a package is
 * located, and where it is unpacked to. It unpacks from the "packages" folder of the current jar it is
 * executing in.
 */

public class StandardPackage extends Package {

    private List<Path> excludes = new ArrayList();
    private Path rootPath;
    private FileSystem jarFileSystem;
    private long size;

    public StandardPackage(String packageName) {
        String jarFilePath = getRunningJarLocation();
        jarFileSystem = getJarFileSystem(jarFilePath);
        rootPath = jarFileSystem.getPath("packages", packageName);
    }

    public String getRunningJarLocation() {
        try {
            return getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }
        catch(URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private FileSystem getJarFileSystem(String jarPath) {
        FileSystem fs = null;
        Path jar = Paths.get(jarPath);

        try {
            fs = FileSystems.newFileSystem(jar, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }

    private void calculatePackageSize() {
        SizeFileWalker sizeCalculator = new SizeFileWalker();
        sizeCalculator.setPackage(this);
        try {
            Files.walkFileTree(rootPath, sizeCalculator);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        size = sizeCalculator.getSize();
    }

    public long getSize() {
        return size;
    }

    public void addExclude(String excludePathName) {
        Path excludePath = jarFileSystem.getPath(rootPath.toString(), excludePathName);
        excludePath = excludePath.toAbsolutePath();
        excludes.add(excludePath);
        calculatePackageSize();
    }

    @Override
    public List<Path> getExcludes() {
        return excludes;
    }

    @Override
    public Path getRootPath() {
        return rootPath;
    }

}
