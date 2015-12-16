package models.unpacking;

import models.InstallerModel;
import models.packaging.StandardPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

/**
 * Created by eunderhi on 04/12/15.
 * The controller that performs the actual unpacking action. This is basically a controller class
 * wrapping on the UnpackFileWalker class. UnpackFileWalker does the heavy lifting and traverses
 * the directory tree, unpacking as it goes. This class basically makes a new fileWalker, gives
 * it the information from the package it is set to unpack, and then calls the filewalker as its
 * action.
 */

public class Unpacker extends InstallerModel {

    private StandardPackage packageToUnpack;
    private UnpackFileWalker walker = new UnpackFileWalker();
    private Stack<Path> filesUnpacked = new Stack<>();
    private long unpackedAmount = 0;

    public Unpacker(StandardPackage thePackage) {
        setPackage(thePackage);
    }

    public void unpack() {
        try {
            Files.walkFileTree(getRootPath(), walker);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPackage(StandardPackage packageToUnpack) {
        this.packageToUnpack = packageToUnpack;
        walker.setPackage(packageToUnpack);
        walker.setUnpacker(this);
    }

    private Path getRootPath() {
        return packageToUnpack.getRootPath();
    }

    public void addUnpackedFile(Path path, long size) {
        filesUnpacked.add(path);
        unpackedAmount += size;
        notifyListeners();
    }

    public Stack<Path> getUnpackedFiles() {
        return filesUnpacked;
    }

    public long getSize() {
        return packageToUnpack.getSize();
    }

    public long getUnpackedAmount() {
        return unpackedAmount;
    }

}
