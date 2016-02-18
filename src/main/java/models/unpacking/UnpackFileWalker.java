package models.unpacking;

import models.packaging.utils.FileWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by eunderhi on 01/12/15.
 * Walks a zip filesystem and unpacks files. Pays attention to excludes, which it does not unpack.
 */
public class UnpackFileWalker extends FileWalker {

    private Unpacker unpacker;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {

        copyFileToInstallationDirectory(file);
        unpacker.addUnpackedFile(file, attr.size());

        return FileVisitResult.CONTINUE;
    }

    private void copyFileToInstallationDirectory(Path file) throws IOException {
        Path destination = Paths.get(getUnpackDirectory().toString(), file.subpath(1, file.getNameCount()).toString());
        File destinationFile = destination.toFile();
        destinationFile.mkdirs();
        Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
        if(destinationFile.getName().endsWith(".sh")) {
            destinationFile.setExecutable(true);
        }
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        exc.printStackTrace();
        return FileVisitResult.CONTINUE;
    }

    public Path getUnpackDirectory() {
        return thePackage.getUnpackDirectory();
    }

    public void setUnpacker(Unpacker unpacker) {
        this.unpacker = unpacker;
    }

}
