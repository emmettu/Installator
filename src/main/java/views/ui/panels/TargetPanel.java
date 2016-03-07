package views.ui.panels;


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
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        getJComponent().add(Box.createGlue(), gbc);
        getJComponent().setBorder(BorderFactory.createEmptyBorder());
        addComponent(new GUITextInputField(), gbc);
        gbc.gridx += 1;
        addComponent(new GUIButton("Install"), gbc);
    }

    public void addComponent(GUIComponent component, GridBagConstraints gbc) {
        getJComponent().add(component.getJComponent(), gbc);
    }

}
