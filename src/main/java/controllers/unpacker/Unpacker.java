package controllers.unpacker;

import controllers.Controller;
import models.packaging.StandardPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by eunderhi on 04/12/15.
 */

public class Unpacker implements Controller {

    private StandardPackage packageToUnpack;

    @Override
    public void performAction() {
        unpack();
    }

    private void unpack() {
        Path rootPath = packageToUnpack.getRootPath();
        UnpackFileWalker walker = new UnpackFileWalker();
        walker.setRootPath(rootPath);
        walker.setExcludes(packageToUnpack.getExcludes());
        walker.setUnpackDirectory(packageToUnpack.getUnpackDirectory());

        try {
            Files.walkFileTree(rootPath, walker);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPackageToUnpack(StandardPackage packageToUnpack) {
        this.packageToUnpack = packageToUnpack;
    }
}
