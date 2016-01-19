package controllers.button;

import controllers.Controller;
import views.ui.UserInputView;
import views.ui.button.GUIButton;

import javax.swing.*;

/**
 * Created by eunderhi on 19/01/16.
 */
public class EnableButtonController implements Controller {

    private GUIButton button;
    private UserInputView view;

    @Override
    public void performAction() {
        JButton jButton = button.getButton();
        jButton.setEnabled(view.isValid());
    }

    public void setButton(GUIButton button) {
        this.button = button;
    }

    public void setView(UserInputView view) {
        this.view = view;
    }
}
