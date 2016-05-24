package views.ui.panels;

import models.packaging.utils.PackageSet;
import views.ui.gui.GUINavPanel;
import views.ui.gui.GUIPanel;
import views.ui.gui.GUIProgressBar;
import views.ui.gui.PackageProgressPanel;

import java.awt.*;

/**
 * Created by eunderhi on 16/03/16.
 * Panel that displays unpacking progress
 */
public class UnpackPanel extends GUIPanel {

    private GUIPanel contentPanel;
    private GUINavPanel buttonPanel;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public UnpackPanel(PackageSet set, String[] names) {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        contentPanel.setSize(WIDTH, HEIGHT);
        buttonPanel = new GUINavPanel();
        //build();
        contentPanel.addComponent(new PackageProgressPanel(set, names));
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.getNext().setEnabled(false);
    }

    private void build() {
        GridBagConstraints constraints = getBasicNoInsetsConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        GUIProgressBar packProgressBar = new GUIProgressBar();
        contentPanel.addComponent(packProgressBar, constraints);
        // make sure there is some space between the progress bars
        constraints.gridy++;
        //contentPanel.addComponent(this.overallOpLabel, constraints);

        GUIProgressBar overallProgressBar = new GUIProgressBar();
        constraints.gridy++;
        contentPanel.addComponent(overallProgressBar, constraints);
    }

    private GridBagConstraints getBasicNoInsetsConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public GUINavPanel getButtonPanel() {
        return buttonPanel;
    }

}
