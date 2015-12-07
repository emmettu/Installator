package controllers.unpacker;

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

    private List<Path> excludes;
    private Path rootPath;
    private Path unpackDirectory;
    private long unpackAmount;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        copyFileToInstallationDirectory(file);
        unpackAmount += attr.size();
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

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        return checkExcludes(dir);
    }

    FileVisitResult checkExcludes(Path file) {
        String fileName = file.toAbsolutePath().toString();
        for(Path path : excludes) {
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
