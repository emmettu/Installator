package views.ui.gui;

import java.awt.*;

/**
 * Created by eunderhi on 29/01/16.
 */
public class GUIPanel extends GUIComponent {

    public GUIPanel() {
        getJComponent().setLayout(new FlowLayout());
    }

    public void clear() {
        getJComponent().removeAll();
    }

}
