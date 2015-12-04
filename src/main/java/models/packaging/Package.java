package models.packaging;

import models.InstallerModel;
import models.Model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by eunderhi on 26/11/15.
 */
public abstract class Package extends InstallerModel {

    private String path;
    private Path unpackDirectory;
    List<Package> subPacks;

    public Path getUnpackDirectory() {
        return unpackDirectory;
    }

    public void setUnpackDirectory(String unpackDirectory) {
        this.unpackDirectory = Paths.get(unpackDirectory);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
