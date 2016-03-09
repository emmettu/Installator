package views.ui.panels;


import controllers.textinput.PathValidator;
import models.validation.FailValidationAction;
import models.validation.Validation;
import views.ui.gui.GUIButton;
import views.ui.gui.GUIComponent;
import views.ui.gui.GUIPanel;
import views.ui.gui.GUITextInputField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */
public class TargetPanel extends GUIPanel {

    public TargetPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addComponent(getTextInputField(), gbc);
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        getJComponent().add(Box.createGlue(), gbc);
        getJComponent().setBorder(BorderFactory.createEmptyBorder());
        gbc.gridx += 1;
        addComponent(new GUIButton("Install"), gbc);
    }

    public void addComponent(GUIComponent component, GridBagConstraints gbc) {
        getJComponent().add(component.getJComponent(), gbc);
    }

    private GUITextInputField getTextInputField() {
        GUITextInputField field = new GUITextInputField();
        field.validation().add(new PathValidator());
        field.validation().addHook(new FailValidationAction(field), Validation.Type.FAIL);
        return field;
    }

}
