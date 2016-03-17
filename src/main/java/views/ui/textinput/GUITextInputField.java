package views.ui.textinput;

import controllers.exceptions.ControllerFailException;
import views.notification.ToastMessage2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by eunderhi on 09/12/15.
 */
public class GUITextInputField extends TextInputField {

    private JTextField textField = new JTextField();
    private JPanel panel = new JPanel();

    public GUITextInputField() {

        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
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
       panel.repaint();
    }

    @Override
    protected void onValidationFail(ControllerFailException e) {
        new ToastMessage2(e.getMessage(), getTextField());
    }

    public JPanel getTextField() {
        return panel;

    }

}
