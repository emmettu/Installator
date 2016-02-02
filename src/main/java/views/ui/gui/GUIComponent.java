package views.ui.gui;

import views.ui.UIComponent;

import javax.swing.*;

/**
 * Created by eunderhi on 25/01/16.
 */
public abstract class GUIComponent extends UIComponent {

    private JComponent jComponent;

    @Override
    public void update() {
       notifyControllers();
    }

    @Override
    public void display() {
        jComponent.repaint();
    }

    public void addComponent(GUIComponent component) {
        JComponent jComponent = component.getJComponent();
        this.jComponent.add(jComponent);
    }

    public void setjComponent(JComponent jComponent) {
        this.jComponent = jComponent;
    }

    public JComponent getJComponent() {
        return jComponent;
    }

    public void setEnabled(boolean enabled) {
        jComponent.setEnabled(enabled);
    }


}
