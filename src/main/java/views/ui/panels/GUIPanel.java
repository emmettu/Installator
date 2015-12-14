package views.ui.panels;

import javax.swing.*;

/**
 * Created by eunderhi on 09/12/15.
 */
public abstract class GUIPanel extends Panel {

    protected JFrame frame;

    public GUIPanel(JFrame frame) {
        this.frame = frame;
        frame.setVisible(true);
    }

    @Override
    protected void displayPanel() {
    }

    public abstract void build();

}
