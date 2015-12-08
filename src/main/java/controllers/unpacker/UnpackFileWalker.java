package controllers.unpacker;

import models.packaging.Package;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by eunderhi on 01/12/15.
 * Walks a zip filesystem and unpacks files. Pays attention to excludes, which it does not unpack.
 */
public class UnpackFileWalker extends SimpleFileVisitor<Path> {

    private long unpackAmount;
    private Package packageToUnpack;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        copyFileToInstallationDirectory(file);
        unpackAmount += attr.size();
        return FileVisitResult.CONTINUE;
    }

    private void copyFileToInstallationDirectory(Path file) {
        Path destination = Paths.get(getUnpackDirectory().toString(), file.subpath(1, file.getNameCount()).toString());
        File destinationFile = destination.toFile();
        destinationFile.mkdirs();
        try {
            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        return checkExcludes(dir);
    }

    FileVisitResult checkExcludes(Path file) {
        String fileName = file.toAbsolutePath().toString();
        for(Path path : getExcludes()) {
            String excludeName = path.toString() + "/";
            if(fileName.equals(excludeName)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    public Path getRootPath() {
        return packageToUnpack.getRootPath();
    }

    public List<Path> getExcludes() {
        return packageToUnpack.getExcludes();
    }

    public void setPackage(Package packageToUnpack) {
        this.packageToUnpack = packageToUnpack;
    }

    public Path getUnpackDirectory() {
        return packageToUnpack.getUnpackDirectory();
    }
}
