package models.packaging;

import models.InstallerModel;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by eunderhi on 31/03/16.
 */

public class InstallLocationModel extends InstallerModel {

    private static final String name = "wildfly-10.0.0.CR4";
    private Path installLocation;

    public InstallLocationModel() {
        installLocation = Paths.get(System.getProperty("user.home"));
    }

    public Path getInstallLocation() {
        return installLocation.resolve(name);
    }

    public void setInstallLocation(Path path) {
        installLocation = path;
        notifyListeners();
    }

    public void setInstallLocation(String path) {
        installLocation = Paths.get(path);
        notifyListeners();
    }

}
