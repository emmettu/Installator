package views.ui.gui;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 24/03/16.
 * Panel where the user creates server administration users
 */
public class UserCreationPanel extends InstallerPanel {

    private FancyGUITextField userNameField;
    private FancyGUIPasswordField passwordField;
    private FancyGUIPasswordField confirmPasswordField;

    public UserCreationPanel(String title, String description) {
        super(title, description);
        addValidation();
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        userNameField = new FancyGUITextField();
        passwordField = new FancyGUIPasswordField();
        confirmPasswordField = new FancyGUIPasswordField();
        int row = 2;
        userNameField.setColumns(16);
        passwordField.setColumns(16);
        confirmPasswordField.setColumns(16);
        contentPanel.addComponent(new GUILabel("Admin username:"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(userNameField, Constraints.createAlignedElementConstraint(row, 0, 120, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Admin password:"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(passwordField, Constraints.createAlignedElementConstraint(row, 0, 120, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Confirm password:"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(confirmPasswordField, Constraints.bottomElement(row, 0, 120, GridBagConstraints.NONE));
    }

    public void addValidation() {
        userNameField.validation().add(illegalCharValidator("$"));
        userNameField.validation().add(emptinessValidator("Username is empty"));
        passwordField.validation().add(illegalCharValidator("$"));
        confirmPasswordField.validation().add(matchValidator(passwordField));
    }

    private Validator<String> emptinessValidator(String message) {
        return (String data) -> {
            if (data.isEmpty()) {
                throw new ControllerFailException(message);
            }
        };
    }

    private Validator<String> matchValidator(TextInputField mustMatch) {
        return (String data) -> {
            if (!data.equals(mustMatch.getText())) {
                throw new ControllerFailException("passwords must match");
            }
        };
    }

    private Validator<String> illegalCharValidator(String illegalChar) {
        return (String data) -> {
            if (data.contains(illegalChar)) {
                throw new ControllerFailException("Field cannot contain " + illegalChar);
            }
        };
    }

}
