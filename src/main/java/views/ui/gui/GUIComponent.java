package views.ui.gui;

import views.ui.UIComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by eunderhi on 25/01/16.
 * Class that wraps a jcomponent and provides a controller notification api
 */
public abstract class GUIComponent extends UIComponent {

    private JComponent jComponent;

    @Override
    public void update() {
       notifyControllers();
    }

    @Override
    public void display() {
        jComponent.revalidate();
        jComponent.repaint();
    }

    public void setJComponent(JComponent comp) {
        jComponent = comp;
    }

    public JComponent getJComponent() {
        return jComponent;
    }

    public void setEnabled(boolean enabled) {
        jComponent.setEnabled(enabled);
    }

    public boolean isEnabled() {
        return jComponent.isEnabled();
    }

    public void setBorder(Border border) {
        jComponent.setBorder(border);
    }

    public void setVisible(boolean visibility) {
        jComponent.setVisible(visibility);
    }

    public void setWidth(int width) {
        int height = jComponent.getPreferredSize().height;
        jComponent.setPreferredSize(new Dimension(width, height));
    }

}
