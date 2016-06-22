package controllers.models;

import controllers.Controller;
import models.panels.UserModel;
import views.ui.gui.TextInputField;
import views.ui.gui.panels.UserCreationPanel;

/**
 * Created by eunderhi on 22/06/16.
 */
public class UserController implements Controller {

    private UserModel model;
    private TextInputField userField;
    private TextInputField passwordField;

    public UserController(UserCreationPanel panel, UserModel model) {
        this.model = model;
        subscribeToPanel(panel);
    }

    @Override
    public void performAction() {
        model.setUserName(userField.getText());
        model.setPassword(passwordField.getText());
    }

    private void subscribeToPanel(UserCreationPanel panel) {
        userField = panel.getUserField();
        passwordField = panel.getPasswordField();
    }

}
