package views.ui.gui;

import views.ui.gui.layout.Constraints;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

/**
 * Created by eunderhi on 03/06/16.
 * Base Installer panel
 */
public abstract class InstallerPanel extends GUIPanel {

    private GUINavPanel navPanel;
    private GUIPanel contentPanel;

    public InstallerPanel(String title, String description) {
        setLayout(new BorderLayout());
        contentPanel = new GUIPanel(new GridBagLayout());
        addTitleAndDescription(title, description);
        navPanel = new GUINavPanel();
        build(contentPanel);
        addComponent(contentPanel, BorderLayout.CENTER);
        addComponent(navPanel, BorderLayout.SOUTH);
    }

    private void addTitleAndDescription(String title, String description) {
        contentPanel.addComponent(Labels.title(title), Constraints.getTitleConstraints());
        contentPanel.addComponent(Labels.intro(description), Constraints.getBasicConstraints());
    }

    protected abstract void build(GUIPanel contentPanel);

    public void setNavPanel(GUINavPanel panel) {
        this.navPanel = panel;
    }

    public GUINavPanel getButtonPanel() {
        return navPanel;
    }

}
