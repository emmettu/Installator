package views.ui.gui;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.UiResources;
import views.notification.ToastMessage;
import views.ui.UIComponent;

import javax.swing.*;
import javax.swing.border.Border;

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
