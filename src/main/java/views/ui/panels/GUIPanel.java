package views.ui.panels;

import javax.swing.*;

/**
 * Created by eunderhi on 09/12/15.
 */
public abstract class GUIPanel extends Panel {

    protected JFrame frame;

    @Override
    protected void displayPanel() {

    }

    public abstract void build();

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
