package views.ui.gui;

/**
 * Created by eunderhi on 24/03/16.
 */
public class FancyGUIPasswordField extends FancyGUITextField {

    @Override
    protected void setField() {
        field = new GUIPasswordField();
    }
}
