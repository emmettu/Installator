package views.ui.panels;

import controllers.textinput.PathValidator;
import models.validation.FailValidationAction;
import models.validation.Validation;
import views.ui.gui.*;
import views.ui.gui.GUIPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */
public class TargetPanel extends GUIPanel {

    GUIPanel contentPanel;
    GUIPanel buttonPanel;

    public TargetPanel() {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        buttonPanel = new GUIButtonPanel();
        build();
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
    }

    private GUITextInputField getTextInputField() {
        GUITextInputField field = new GUITextInputField();
        field.validation().add(new PathValidator());
        field.validation().addHook(new FailValidationAction(field), Validation.Type.FAIL);
        return field;
    }

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
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        gbc.gridx += 1;
        contentPanel.addComponent(new GUIButton("Install"), gbc);
    }

}
