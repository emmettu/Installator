package views.ui.gui.panels;

import views.ui.gui.*;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 24/06/16.
 */
public class LDAPPanel extends InstallerPanel {

    private FancyGUITextField connectionName;
    private FancyGUITextField directoryServer;
    private FancyGUITextField distinguishedName;
    private FancyGUIPasswordField password;
    private FancyGUIPasswordField confirmPassword;
    private static final int INDENT = 200;
    private static final int BOTTOM_SPACE = 200;

    public LDAPPanel(String title, String description) {
        super(title, description);
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        initializeComponents();
        int row = 2;
        contentPanel.addComponent(new GUILabel("Connection Name"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(connectionName, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Directory Server"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(directoryServer, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Distinguished Name (DN)"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(distinguishedName, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("DN Password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(password, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("DN Confirm Password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(confirmPassword, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUIPanel(), Constraints.bottomElement(row, INDENT, BOTTOM_SPACE));
    }

    private void initializeComponents() {
        connectionName = new FancyGUITextField();
        directoryServer = new FancyGUITextField();
        distinguishedName = new FancyGUITextField();
        password = new FancyGUIPasswordField();
        confirmPassword = new FancyGUIPasswordField();
        connectionName.setColumns(16);
        directoryServer.setColumns(16);
        distinguishedName.setColumns(16);
        password.setColumns(16);
        confirmPassword.setColumns(16);
        addValidators();
    }

    private void addValidators() {

    }

    public FancyGUITextField getConnectionName() {
        return connectionName;
    }
    public FancyGUITextField getDirectoryServer() {
        return directoryServer;
    }
    public FancyGUITextField getDistinguishedName() {
        return distinguishedName;
    }
    public FancyGUIPasswordField getPassword() {
        return password;
    }

}
