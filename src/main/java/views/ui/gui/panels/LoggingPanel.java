package views.ui.gui.panels;

import models.panels.logging.LoggingLevel;
import views.ui.gui.GUICombobox;
import views.ui.gui.GUILabel;
import views.ui.gui.GUIPanel;
import views.ui.gui.InstallerPanel;
import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 25/07/16.
 */
public class LoggingPanel extends InstallerPanel {

    private GUICombobox<LoggingLevel> root;
    private GUICombobox<LoggingLevel> console;
    private static final int INDENT = 150;
    private static final int BOTTOM_SPACE = 200;

    public LoggingPanel(String title, String description) {
        super(title, description);
    }

    @Override
    protected void build(GUIPanel contentPanel) {
        initializeComponents();
        int row = 2;
        contentPanel.addComponent(new GUILabel("Root logging level"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(root, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUILabel("Console logging level"), Constraints.createFullLineElementConstraint(row, 0, 0));
        contentPanel.addComponent(console, Constraints.createAlignedElementConstraint(row, 0, INDENT, GridBagConstraints.NONE));
        row++;
        contentPanel.addComponent(new GUIPanel(), Constraints.bottomElement(row, INDENT, BOTTOM_SPACE));
    }

    private void initializeComponents() {
        root = new GUICombobox<>(LoggingLevel.values());
        console = new GUICombobox<>(LoggingLevel.values());
    }

    public GUICombobox<LoggingLevel> getRoot() {
        return root;
    }

    public GUICombobox<LoggingLevel> getConsole() {
        return console;
    }

}
