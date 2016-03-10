package views.ui.panels;

import controllers.textinput.PathValidator;
import models.validation.FailValidationAction;
import models.validation.Validation;
import views.ui.gui.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */
public class TargetPanel extends GUIFramePanel {

    public TargetPanel() {
        super("Target Panel");
    }

    private GUITextInputField getTextInputField() {
        GUITextInputField field = new GUITextInputField();
        field.validation().add(new PathValidator());
        field.validation().addHook(new FailValidationAction(field), Validation.Type.FAIL);
        return field;
    }

    @Override
    protected void build() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.addComponent(getTextInputField(), gbc);
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        contentPanel.getJComponent().add(Box.createGlue(), gbc);
        contentPanel.getJComponent().setBorder(BorderFactory.createEmptyBorder());
        gbc.gridx += 1;
        contentPanel.addComponent(new GUIButton("Install"), gbc);
    }

    @Override
    protected void buildButtonPanel() {
        buttonPanel.addComponent(new GUIButtonPanel());
    }

}
