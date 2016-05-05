package models.panels;

import models.InstallerModel;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Created by eunderhi on 14/04/16.
 * Model that holds SSL data
 */
public class SSLModel extends InstallerModel {

    private Path keyStoreLocation;
    private String password;
    private String realm;
    private Optional<VaultModel> vault = Optional.empty();

    public Path getKeyStoreLocation() {
        return keyStoreLocation;
    }

    public void setKeyStoreLocation(Path keyStoreLocation) {
        this.keyStoreLocation = keyStoreLocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVault(VaultModel vault) {
        this.vault = Optional.of(vault);
    }

    public void setVault(Optional<VaultModel> vault) {
        this.vault = vault;
    }

    public void vaultPassword() {
        vault.ifPresent(v -> password = v.vaultPassword("ssl", "password", password));
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

}
