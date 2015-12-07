package controllers.unpacker;

import controllers.Controller;
import models.packaging.StandardPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by eunderhi on 04/12/15.
 * The controller that performs the actual unpacking action. This is basically a controller class
 * wrapping on the UnpackFileWalker class. UnpackFileWalker does the heavy lifting and traverses
 * the directory tree, unpacking as it goes. This class basically makes a new fileWalker, gives
 * it the information from the package it is set to unpack, and then calls the filewalker as its
 * action.
 */

public class Unpacker implements Controller {

    private StandardPackage packageToUnpack;
    UnpackFileWalker walker = new UnpackFileWalker();
    Path rootPath;

    @Override
    public void performAction() {
        unpack();
    }

    private void unpack() {

        try {
            Files.walkFileTree(rootPath, walker);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPackageToUnpack(StandardPackage packageToUnpack) {
        rootPath = packageToUnpack.getRootPath();
        walker.setRootPath(rootPath);
        walker.setExcludes(packageToUnpack.getExcludes());
        walker.setUnpackDirectory(packageToUnpack.getUnpackDirectory());
        this.packageToUnpack = packageToUnpack;
    }

}
