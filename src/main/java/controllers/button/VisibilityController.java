package controllers.button;

import controllers.Controller;
import views.ui.UserInputView;
import views.ui.button.GUIButton;
import views.ui.gui.Button;
import views.ui.gui.GUIComponent;

import javax.swing.*;

/**
 * Created by eunderhi on 19/01/16.
 */
public class VisibilityController implements Controller {

    private GUIComponent component;
    private boolean visibility;

    public VisibilityController(GUIComponent component, boolean visibility) {
        this.component = component;
        this.visibility = visibility;
    }

    @Override
    public void performAction() {
        component.setEnabled(visibility);
    }

}
