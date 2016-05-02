package models.panels;

import models.InstallerModel;
import org.jboss.as.security.vault.VaultSession;

import java.nio.file.Path;

/**
 * Created by eunderhi on 04/04/16.
 * The model class that holds information on the vault.
 */
public class VaultModel extends InstallerModel {

    private String alias;
    private String salt;
    private int iterationCount;
    private String password;
    private Path storeLocation;
    private Path encrDirectory;
    private VaultSession vaultSession;

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

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
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

    public Path getEncrDirectory() {
        return encrDirectory;
    }

    public void setEncrDirectory(Path encrDirectory) {
        this.encrDirectory = encrDirectory;
        notifyListeners();
    }

    public VaultSession getVaultSession() {
        if (vaultSession == null) {
            createSession();
        }
        return vaultSession;
    }

    public String vaultPassword(String block, String attribute, String password) {
        vaultSession = getVaultSession();
        try {
            return "${" +
                    vaultSession.addSecuredAttribute(block, attribute, password.toCharArray()) +
                    "}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    private void createSession() {
        try {
            vaultSession = new VaultSession(
                    storeLocation.toString(),
                    password,
                    encrDirectory.toString(),
                    salt,
                    iterationCount,
                    true);
            vaultSession.startVaultSession(alias);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
