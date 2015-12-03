package models.packaging;

import models.packaging.utils.UnpackFileWalker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 30/11/15.
 * This the base implementation of the package class.
 * It stores information about where a package is
 * located, where it is unpacked to, and how it is
 * unpacked.
 */

public class StandardPackage extends Package {

    private List<Path> excludes = new ArrayList();
    private Path rootPath;
    private FileSystem jarFileSystem;

    public StandardPackage(String packageName) {
        String jarFilePath = getRunningJarLocation();
        jarFileSystem = getJarFileSystem(jarFilePath);
        rootPath = jarFileSystem.getPath("packages", packageName);
    }

    private String getRunningJarLocation() {
        try {
            return getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }
        catch(URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void unpack() {
        UnpackFileWalker walker = new UnpackFileWalker();
        walker.setRootPath(rootPath);
        walker.setExcludes(excludes);
        walker.setUnpackDirectory(getUnpackDirectory());

        try {
            Files.walkFileTree(rootPath, walker);
        }
        catch(IOException e) {
            e.printStackTrace();
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

    public void addExclude(String excludePathName) {
        Path excludePath = jarFileSystem.getPath(rootPath.toString(), excludePathName);
        excludePath = excludePath.toAbsolutePath();
        excludes.add(excludePath);
    }

    public void setExcludes(List<Path> excludes) {
        this.excludes = excludes;
    }
}
