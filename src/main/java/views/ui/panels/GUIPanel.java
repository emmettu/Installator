package views.ui.panels;

import views.ui.button.GUIButton;
import views.ui.textinput.GUITextInputField;

import javax.swing.*;

/**
 * Created by eunderhi on 09/12/15.
 */
public abstract class GUIPanel extends Panel {

    protected JFrame frame;
    protected GUITextInputField field;
    protected GUIButton button;

    public GUIPanel(JFrame frame) {
        this.frame = frame;
        frame.setVisible(true);
    }

    @Override
    protected void displayPanel() {
        SwingUtilities.invokeLater(() -> field.display());
    }

    public abstract void build();

}
