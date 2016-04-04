package models.panels;

import models.InstallerModel;

import java.nio.file.Path;

/**
 * Created by eunderhi on 04/04/16.
 */
public class VaultModel extends InstallerModel {

    private String alias;
    private String salt;
    private String iterationCount;
    private String password;
    private Path storeLocation;
    private Path fileDirectory;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
        notifyListeners();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
        notifyListeners();
    }

    public String getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(String iterationCount) {
        this.iterationCount = iterationCount;
        notifyListeners();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyListeners();
    }

    public Path getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Path storeLocation) {
        this.storeLocation = storeLocation;
        notifyListeners();
    }

    public Path getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(Path fileDirectory) {
        this.fileDirectory = fileDirectory;
        notifyListeners();
    }

}
