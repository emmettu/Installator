package views.ui.textinput;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.UiResources;
import views.notification.ToastMessage;

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
    protected void onValidationFail(ControllerFailException e) {
        textField.setToolTipText(e.getMessage());
        textField.setBackground(UiResources.validationFail);
        new ToastMessage(e.getMessage(), 4000, textField);
    }

    @Override
    protected void onValidationSuccess() {
        textField.setBackground(Color.WHITE);
    }

    public JTextField getTextField() {
        return textField;

    }

    @Override
    protected void onControllerFail(ControllerFailException e) {

    }

    @Override
    protected void onControllerWarn(ControllerWarnException e) {

    }
}
