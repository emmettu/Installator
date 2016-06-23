package views.ui.gui.panels;

import views.ui.gui.*;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 23/06/16.
 */
public class SSLPanel extends InstallerPanel {

    private FancyGUITextField sslPassword;
    private FancyGUIPasswordField sslConfirmPassword;
    private GUIPathComponent keystoreLocation;
    private static final int INDENT = 200;
    private static final int BOTTOM_FILL = 250;

    public SSLPanel(String title, String description) {
        super(title, description);
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        initializeComponents();
        int row = 2;
        contentPanel.addComponent(new GUILabel("SSL keystore password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(sslPassword, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Confirm SSL keystore password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(sslConfirmPassword, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Keystore location"), Constraints.createFullLineElementConstraint(row, 0, 0));
        row++;
        contentPanel.addComponent(keystoreLocation, Constraints.fieldConstraints(row));
        contentPanel.addComponent(new GUIPanel(), Constraints.bottomElement(row, INDENT, BOTTOM_FILL));
    }

    private void initializeComponents() {
        sslPassword = new FancyGUIPasswordField();
        sslConfirmPassword = new FancyGUIPasswordField();
        keystoreLocation = GUIPathComponent.existingFileComponent();
        sslPassword.setColumns(16);
        sslConfirmPassword.setColumns(16);
        addValidators();
    }

    private void addValidators() {

    }

}
