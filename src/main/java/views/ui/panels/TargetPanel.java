package views.ui.panels;

import views.ui.gui.*;
import views.ui.gui.GUIPanel;

import java.awt.*;

/**
 * Created by eunderhi on 24/02/16.
 * The panel where user selects the installation path
 */

public class TargetPanel extends GUIPanel {

    private GUIPanel contentPanel;
    private GUIButtonPanel buttonPanel;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private GUIPathComponent field = new GUIPathComponent();

    public TargetPanel() {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        contentPanel.setSize(WIDTH, HEIGHT);
        buttonPanel = new GUIButtonPanel();
        build();
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
    }

    private void build() {
        GridBagConstraints gbc = getBasicConstraints();

        //JLabel info = LabelFactory.create(idata.langpack.getString("TargetPanel.info"));
        gbc.gridy++;
        //contentPanel.add(info, gbc);
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

    public GUIButtonPanel getButtonPanel() {
        return buttonPanel;
    }

}
