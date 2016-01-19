package views.ui.textinput;

import controllers.exceptions.ValidationException;
import views.lookandfeel.UiResources;

import javax.swing.*;
import java.awt.*;
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
        validate();
        notifyControllers();
    }

    @Override
    public void display() {
       textField.repaint();
    }

    @Override
    protected void onValidationFail(ValidationException e) {
        textField.setToolTipText(e.getMessage());
        textField.setBackground(UiResources.validationFail);
    }

    @Override
    protected void onValidationSuccess() {
        textField.setBackground(Color.WHITE);
    }

    public JTextField getTextField() {
        return textField;

    }

}
