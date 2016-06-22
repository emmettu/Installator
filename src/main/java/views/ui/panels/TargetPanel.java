package views.ui.panels;

import views.ui.gui.*;
import views.ui.gui.GUIPanel;

import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */

public class TargetPanel extends InstallerPanel {

    private GUIPathComponent field;

    public TargetPanel(String title, String description) {
        super(title, description);
    }

    @Override
    public void build(GUIPanel contentPanel) {
        field = GUIPathComponent.newDirectoryComponent();
        GridBagConstraints gbc = getBasicConstraints();
        gbc.gridy++;
        gbc.gridy++;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.addComponent(field, gbc);
    }

    private GridBagConstraints getBasicConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, 20, 2, 20);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public FancyGUITextField getField() {
        return field.getField();
    }

}
