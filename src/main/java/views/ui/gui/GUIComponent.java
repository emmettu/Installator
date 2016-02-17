package views.ui.gui;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.UiResources;
import views.notification.ToastMessage;
import views.ui.UIComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 25/01/16.
 */
public abstract class GUIComponent extends UIComponent {

    private JPanel jComponent = new JPanel(new BorderLayout());

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

    public void setJComponent(JComponent comp) {
        jComponent.add(comp, BorderLayout.NORTH);
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

    @Override
    protected void onControllerFail(ControllerFailException e) {
        jComponent.setToolTipText(e.getMessage());
        jComponent.setBackground(UiResources.validationFail);
        new ToastMessage(e.getMessage(), 4000, jComponent);
    }

    @Override
    protected void onControllerWarn(ControllerWarnException e) {
        jComponent.setToolTipText(e.getMessage());
        jComponent.setBackground(UiResources.validationWarn);
        new ToastMessage(e.getMessage(), 4000, jComponent);
    }

    @Override
    protected void onControllerSuccess() {}

}
