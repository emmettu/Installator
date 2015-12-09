package views.ui.panels;

import views.ui.textinput.GUITextInputField;

import javax.swing.*;

/**
 * Created by eunderhi on 09/12/15.
 */
public class GUIPanel extends Panel {

    private JFrame frame;
    private GUITextInputField field;

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
        frame.getContentPane().add(field.getTextField());
        frame.pack();
    }
}
