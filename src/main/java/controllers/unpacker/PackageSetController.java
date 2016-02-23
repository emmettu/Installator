package controllers.unpacker;

import controllers.Controller;
import models.packaging.utils.PackageSet;
import models.unpacking.Unpacker;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by eunderhi on 23/02/16.
 */
public class PackageSetController implements Controller {

    private PackageSet packages;
    private Unpacker unpacker;

    public PackageSetController(PackageSet packages, Unpacker unpacker) {
        this.packages = packages;
        this.unpacker = unpacker;
    }

    @Override
    public void performAction() {
        try {
            packages.addSize(Files.size(unpacker.getUnpackedFiles().peek()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
