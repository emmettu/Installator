package controllers.models;

import controllers.Controller;
import models.panels.LdapModel;
import views.ui.gui.TextInputField;
import views.ui.gui.panels.LDAPPanel;

/**
 * Created by eunderhi on 24/06/16.
 */
public class LDAPController implements Controller {

    private LdapModel model;
    private TextInputField connectionName;
    private TextInputField directoryServer;
    private TextInputField distinguishedName;
    private TextInputField password;

    public LDAPController(LDAPPanel panel, LdapModel model) {
        subscribeToPanel(panel);
        this.model = model;
        model.setRecursive(true);
        model.setFilterType(LdapModel.FilterType.ADVANCED);
        model.setFilter("test");
        model.setBaseDN("test");
        model.setRealmName("ldap");
    }

    @Override
    public void performAction() {
        model.setName(connectionName.getText());
        model.setUrl(directoryServer.getText());
        model.setdN(distinguishedName.getText());
        model.setPassword(password.getText());
    }

    private void subscribeToPanel(LDAPPanel panel) {
        connectionName = panel.getConnectionName();
        directoryServer = panel.getDirectoryServer();
        distinguishedName = panel.getDistinguishedName();
        password = panel.getPassword();
    }

}
