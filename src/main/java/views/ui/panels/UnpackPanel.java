package views.ui.panels;

import views.ui.gui.GUIButtonPanel;
import views.ui.gui.GUIPanel;
import views.ui.gui.GUIProgressBar;

import java.awt.*;

/**
 * Created by eunderhi on 16/03/16.
 */
public class UnpackPanel extends GUIPanel {

    private GUIPanel contentPanel;
    private GUIButtonPanel buttonPanel;
    private int WIDTH = 500;
    private int HEIGHT = 500;

    public UnpackPanel() {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        contentPanel.setSize(WIDTH, HEIGHT);
        buttonPanel = new GUIButtonPanel();
        build();
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(buttonPanel, BorderLayout.SOUTH);
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

    public GUIButtonPanel getButtonPanel() {
        return buttonPanel;
    }

}
