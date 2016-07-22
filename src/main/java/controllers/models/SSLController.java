package controllers.models;

import controllers.Controller;
import models.panels.SSLModel;
import views.ui.gui.TextInputField;
import views.ui.gui.panels.SSLPanel;

/**
 * Created by eunderhi on 23/06/16.
 */
public class SSLController implements Controller {

    private SSLModel model;
    private TextInputField password;
    private TextInputField keystoreLocation;

    public SSLController(SSLPanel panel, SSLModel model) {
        subscribeToPanel(panel);
        this.model = model;
        model.setRealm("ManagementRealm");
    }

    @Override
    public void performAction() {
        model.setPassword(password.getText());
        model.setKeyStoreLocation(keystoreLocation.getText());
    }

    private void subscribeToPanel(SSLPanel panel) {
        password = panel.getSSLPassword();
        keystoreLocation = panel.getKeystoreLocation().getField();
    }

}
