package views.ui.gui;

import javax.swing.*;

/**
 * Created by eunderhi on 11/02/16.
 */
public class GUITextInputField extends GUIComponent implements TextInputField{

    private JTextField field = new JTextField();

    public GUITextInputField() {
        setjComponent(field);
    }

    @Override
    public String getText() {
        return field.getText();
    }

    @Override
    public void setText(String text) {
        field.setText(text);
    }
}
