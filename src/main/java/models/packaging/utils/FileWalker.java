package models.packaging.utils;

import models.packaging.Package;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by eunderhi on 16/12/15.
 */
public abstract class FileWalker extends SimpleFileVisitor<Path> {

    protected Package thePackage;

    protected FileVisitResult checkExcludes(Path file) {
        String fileName = file.toAbsolutePath().toString();
        for(Path path : getExcludes()) {
            String excludeName = path.toString() + "/";
            if(fileName.equals(excludeName)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getExcludes() {
        return thePackage.getExcludes();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        return checkExcludes(dir);
    }

    public void setPackage(Package packageToUnpack) {
        this.thePackage = packageToUnpack;
    }
}
