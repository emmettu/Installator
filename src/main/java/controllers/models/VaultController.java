package controllers.models;

import controllers.Controller;
import models.panels.VaultModel;
import views.ui.gui.TextInputField;
import views.ui.gui.panels.VaultPanel;

/**
 * Created by eunderhi on 20/06/16.
 */
public class VaultController implements Controller {

    private VaultModel model;
    private TextInputField alias;
    private TextInputField salt;
    private TextInputField iterationCount;
    private TextInputField encryptedFileDirectory;
    private TextInputField password;
    private TextInputField keystoreLocation;

    //TODO: VaultPanel should refer to an interface not a concrete class
    public VaultController(VaultPanel panel, VaultModel model) {
        subscribeToPanel(panel);
        this.model = model;
    }

    @Override
    public void performAction() {
        model.setPassword(password.getText());
        model.setAlias(alias.getText());
        model.setSalt(salt.getText());
        model.setIterationCount(iterationCount.getText());
        model.setEncrDirectory(encryptedFileDirectory.getText());
        model.setStoreLocation(keystoreLocation.getText());
    }

    private void subscribeToPanel(VaultPanel panel) {
        alias = panel.getAlias();
        salt = panel.getSalt();
        iterationCount = panel.getIterationCount();
        encryptedFileDirectory = panel.getEncryptedFileDirectory().getField();
        keystoreLocation = panel.getKeyStoreLocation().getField();
        password = panel.getKeystorePassword();
    }

}
