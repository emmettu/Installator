package controllers.button;

import controllers.Controller;
import views.ui.button.GUIButton;

import javax.swing.*;

/**
 * Created by eunderhi on 13/01/16.
 */
public class DisableButtonController implements Controller {

    public GUIButton button;

    public DisableButtonController(GUIButton button) {
        this.button = button;
    }

    @Override
    public void performAction() {
        JButton guiButton = button.getButton();
        guiButton.setVisible(!guiButton.isVisible());
    }

}
