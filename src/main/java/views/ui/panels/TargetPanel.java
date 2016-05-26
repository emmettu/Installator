package views.ui.panels;

import views.ui.gui.*;
import views.ui.gui.GUIPanel;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */

public class TargetPanel extends GUIPanel {

    private GUIPanel contentPanel;
    private GUINavPanel buttonPanel;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private GUIPathComponent field = new GUIPathComponent();

    public TargetPanel() {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        contentPanel.setSize(WIDTH, HEIGHT);
        buttonPanel = new GUINavPanel();
        buttonPanel.getPrev().setVisible(false);
        build();
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
    }

    private void build() {
        contentPanel.addComponent(Labels.title("Installation Path"), Constraints.getTitleConstraints());
        contentPanel.addComponent(Labels.intro("Select the installation path:"), Constraints.getBasicConstraints());
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

    public GUINavPanel getButtonPanel() {
        return buttonPanel;
    }

}
