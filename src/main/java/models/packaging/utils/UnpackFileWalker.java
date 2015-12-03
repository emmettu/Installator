package models.packaging.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by eunderhi on 01/12/15.
 * walks a zip filesystem and unpacks files
 */
public class UnpackFileWalker extends SimpleFileVisitor<Path> {

    private List<Path> excludes;
    private Path rootPath;
    private Path unpackDirectory;


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        copyFileToInstallationDirectory(file);
        return FileVisitResult.CONTINUE;
    }

    private void copyFileToInstallationDirectory(Path file) {
        Path destination = Paths.get(unpackDirectory.toString(), file.subpath(1, file.getNameCount()).toString());
        File destinationFile = destination.toFile();
        destinationFile.mkdirs();
        try {
            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Print each directory visited.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        return checkExcludes(dir);
    }

    FileVisitResult checkExcludes(Path file) {
        String fileName = file.toString() + "/";
        for(Path path : excludes) {
            Path resolvedPath = path.resolve(fileName);
            if(resolvedPath.equals(path)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
        }
        return FileVisitResult.CONTINUE;
    }


    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    public Path getRootPath() {
        return rootPath;
    }

    public void setRootPath(Path rootPath) {
        this.rootPath = rootPath;
    }

    public void setExcludes(List<Path> excludes) {
        this.excludes = excludes;
    }

    public void setUnpackDirectory(Path unpackDirectory) {
        this.unpackDirectory = unpackDirectory;
    }
}
