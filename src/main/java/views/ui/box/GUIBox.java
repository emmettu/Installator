package views.ui.box;

import views.ui.gui.GUIComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 24/05/16.
 */
public class GUIBox extends GUIComponent {

    public static GUIBox createGlue() {
        JComponent component = (JComponent) Box.createGlue();
        return createBox(component);
    }

    public static GUIBox createHorizontalGlue() {
        JComponent component = (JComponent) Box.createHorizontalGlue();
        return createBox(component);
    }

    public static GUIBox createRigidArea(Dimension d) {
        JComponent component = (JComponent) Box.createRigidArea(d);
        return createBox(component);
    }

    private static GUIBox createBox(JComponent c) {
        GUIBox box = new GUIBox();
        box.setJComponent(c);
        return box;
    }

}
