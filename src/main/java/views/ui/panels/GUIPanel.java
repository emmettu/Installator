package views.ui.panels;

import views.ui.button.GUIButton;
import views.ui.textinput.GUITextInputField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 09/12/15.
 */
public class GUIPanel extends Panel {

    private JFrame frame;
    private GUITextInputField field;
    private GUIButton button;

    public GUIPanel(JFrame frame) {
        this.frame = frame;
        frame.setVisible(true);
    }

    @Override
    protected void displayPanel() {
        SwingUtilities.invokeLater(() -> {
            field.display();
        });
    }

    public void setField(GUITextInputField field) {
        this.field = field;
    }

    public void setButton(GUIButton button) {
        this.button = button;
    }

    public void build() {
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(field.getTextField(), BorderLayout.NORTH);
        frame.getContentPane().add(button.getButton(), BorderLayout.SOUTH);
    }

}
