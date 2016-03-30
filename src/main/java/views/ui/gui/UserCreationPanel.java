package views.ui.gui;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.awt.*;

/**
 * Created by eunderhi on 24/03/16.
 */
public class UserCreationPanel extends GUIPanel {

    private FancyGUITextField userNameField;
    private FancyGUIPasswordField passwordField;
    private FancyGUIPasswordField confirmPasswordField;

    public UserCreationPanel() {
        setLayout(new GridLayout(3, 1));
        userNameField = new FancyGUITextField();
        passwordField = new FancyGUIPasswordField();
        confirmPasswordField = new FancyGUIPasswordField();
        addComponent(userNameField);
        addComponent(passwordField);
        addComponent(confirmPasswordField);

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
