package views.ui.gui;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.UiResources;
import views.notification.ToastMessage;
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

}
