package views.ui.gui;

import javax.swing.*;

/**
 * Created by eunderhi on 24/03/16.
 */
public class GUIPasswordField extends GUITextInputField {

    public GUIPasswordField() {
        field = new JPasswordField();
        setUpJComponent();
    }

}
