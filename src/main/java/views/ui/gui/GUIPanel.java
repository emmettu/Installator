package views.ui.gui;

import java.awt.*;

/**
 * Created by eunderhi on 29/01/16.
 */
public class GUIPanel extends GUIComponent {

    public GUIPanel() {
    }

    public void clear() {
        getJComponent().setPreferredSize(new Dimension(900, 600));
        getJComponent().removeAll();
        getJComponent().setLayout(new GridLayout(0, 1));
    }

}
