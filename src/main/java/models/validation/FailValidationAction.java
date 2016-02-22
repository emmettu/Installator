package models.validation;

import views.ui.gui.GUIComponent;

/**
 * Created by eunderhi on 22/02/16.
 */
public class FailValidationAction implements ValidateAction {

    private GUIComponent component;

    public FailValidationAction(GUIComponent component) {
        this.component = component;
    }

    @Override
    public void go(Exception e) {
        System.out.println(e.getMessage());
    }

}
