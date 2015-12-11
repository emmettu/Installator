package views.ui.textinput;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by eunderhi on 09/12/15.
 */
public class GUITextInputField extends TextInputField {

    private JTextField textField = new JTextField();

    public GUITextInputField() {

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {}

            @Override
            public void focusLost(FocusEvent focusEvent) {
                update();
            }
        });
        textField.setSize(10, 15);
    }

    @Override
    public void update() {
        setText(textField.getText());
        notifyControllers();
    }

    @Override
    public void display() {
       textField.repaint();
    }

    public JTextField getTextField() {
        return textField;
    }

}
