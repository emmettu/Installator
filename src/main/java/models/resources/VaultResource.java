package models.resources;

import models.panels.VaultModel;
import models.resources.exceptions.CommandFailedException;

/**
 * Created by eunderhi on 05/04/16.
 */
public class VaultResource {

    ServerResource server;

    public VaultResource (ServerResource server) {
        this.server = server;
    }

    public synchronized void makeVault(VaultModel vault) {
        String masked = vault.getVaultSession().getKeystoreMaskedPassword();
        String command = "/core-service=vault:add(vault-options=[(\"KEYSTORE_URL\" => \""
                + vault.getStoreLocation().toString().replaceAll("\\\\", "/") + "/\"), "
                + "(\"KEYSTORE_PASSWORD\" => \""
                + masked + "\"), "
                + "(\"KEYSTORE_ALIAS\" => \""
                + vault.getAlias() + "\"), "
                + "(\"SALT\" => \""
                + vault.getSalt() + "\"), "
                + "(\"ITERATION_COUNT\" => \""
                + vault.getIterationCount() + "\"), "
                + "(\"ENC_FILE_DIR\" => \""
                + vault.getEncrDirectory().toString().replaceAll("\\\\", "/") + "/\")])";
        try {
            server.submit(command);
        } catch (CommandFailedException e) {
            System.out.println(e.getMessage());
        }
    }

}
