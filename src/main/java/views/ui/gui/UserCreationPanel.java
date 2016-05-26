package views.ui.gui;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 24/03/16.
 */
public class UserCreationPanel extends GUIPanel {

    private FancyGUITextField userNameField;
    private FancyGUIPasswordField passwordField;
    private GUINavPanel buttonPanel;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private FancyGUIPasswordField confirmPasswordField;
    private GUIPanel contentPanel;

    public UserCreationPanel() {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        userNameField = new FancyGUITextField();
        passwordField = new FancyGUIPasswordField();
        confirmPasswordField = new FancyGUIPasswordField();
        buttonPanel = new GUINavPanel();
        contentPanel.setSize(WIDTH, HEIGHT);
        build();
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
        addValidation();
    }

    private void build() {
        contentPanel.addComponent(Labels.title("Create User"), Constraints.getTitleConstraints());
        contentPanel.addComponent(Labels.intro("Create a user to log into the wildfly management interface."), Constraints.getBasicConstraints());
        GridBagConstraints gbc = getBasicConstraints();
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPanel.addComponent(new GUILabel("Admin username:"), gbc);
        //gbc.gridx++;
        gbc.insets = new Insets(2, 0, 2, 20);
        gbc.anchor = GridBagConstraints.NORTH;
        userNameField.setColumns(20);
        contentPanel.addComponent(userNameField, gbc);
    }

    private GridBagConstraints getBasicConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, 20, 2, 0);
//        gbc.gridheight = 1;
//        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public void addValidation() {
        userNameField.validation().add((String data) -> {
            if (data.contains("$")) {
                throw new ControllerFailException("no dollar signs bro");
            }
        });
        userNameField.validation().add((String data) -> {
            if (data.isEmpty()) {
                throw new ControllerWarnException("Username is empty");
            }
        });
        passwordField.validation().add((String data) -> {
            if (data.contains("$")) {
                throw new ControllerFailException("no dollar signs bro");
            }
        });
        confirmPasswordField.validation().add((String data) -> {
            if (!data.equals(passwordField.getText())) {
                throw new ControllerFailException("passwords must match");
            }
        });
    }

}
