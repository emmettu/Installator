package views.ui.gui.panels;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import views.ui.gui.*;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 17/06/16.
 */
public class VaultPanel extends InstallerPanel {

    private FancyGUITextField alias;
    private FancyGUITextField salt;
    private FancyGUITextField iterationCount;
    private FancyGUIPasswordField keystorePassword;
    private FancyGUIPasswordField keystoreConfirmPassword;
    private GUIPathComponent keyStoreLocation;
    private GUIPathComponent encryptedFileDirectory;
    private static final int INDENT = 200;

    public VaultPanel(String title, String description) {
        super(title, description);
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        initializeComponents();
        int row = 2;
        contentPanel.addComponent(new GUILabel("Vault alias"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(alias, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Salt (8-chars)"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(salt, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Iteration count"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(iterationCount, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Vault keystore password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(keystorePassword, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Confirm vault keystore password"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(keystoreConfirmPassword, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("New keystore location"), Constraints.createFullLineElementConstraint(row, 0, 0));
        row++;
        contentPanel.addComponent(keyStoreLocation, Constraints.fieldConstraints(row));
        row++;
        contentPanel.addComponent(new GUILabel("Encrypted file directory"), Constraints.createFullLineElementConstraint(row, 0, 0));
        row++;
        contentPanel.addComponent(encryptedFileDirectory, Constraints.fieldConstraints(row));
        contentPanel.addComponent(new GUIPanel(), Constraints.bottomElement(row, INDENT, 110));
    }

    private void initializeComponents() {
        encryptedFileDirectory = new GUIPathComponent();
        keyStoreLocation = new GUIPathComponent();
        keystoreConfirmPassword = new FancyGUIPasswordField();
        keystorePassword = new FancyGUIPasswordField();
        iterationCount = new FancyGUITextField();
        salt = new FancyGUITextField();
        alias = new FancyGUITextField();
        alias.setColumns(16);
        salt.setColumns(16);
        iterationCount.setColumns(16);
        keystorePassword.setColumns(16);
        keystoreConfirmPassword.setColumns(16);
        addValidators();
    }

    private void addValidators() {
        salt.validation().add(mustBeNChars(8, "Salt is not 8 chars"));
    }

    private Validator<String> mustBeNChars(int n, String message) {
        return (String s)  -> {
            if (s.length() != n) {
                throw new ControllerFailException(message);
            }
        };
    }

}
