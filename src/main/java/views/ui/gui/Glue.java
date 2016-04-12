package views.ui.gui;

import javax.swing.*;

/**
 * Created by eunderhi on 12/04/16.
 */
public class Glue extends GUIComponent {

    public Glue() {
        setJComponent((JComponent) Box.createGlue());
    }

}
