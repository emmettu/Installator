package controllers.models;

import controllers.Controller;
import models.panels.logging.LoggingLevel;
import models.panels.logging.LoggingModel;
import views.ui.gui.Combobox;
import views.ui.gui.panels.LoggingPanel;

/**
 * Created by eunderhi on 25/07/16.
 */
public class LoggingController implements Controller {

    private LoggingModel model;
    private Combobox<LoggingLevel> root;
    private Combobox<LoggingLevel> console;

    public LoggingController(LoggingPanel panel, LoggingModel model) {
        subscribeToPanel(panel);
        this.model = model;
    }

    @Override
    public void performAction() {
        model.setRoot(root.getSelectedItem());
        model.setConsole(console.getSelectedItem());
    }

    private void subscribeToPanel(LoggingPanel panel) {
        console = panel.getConsole();
        root = panel.getRoot();
    }

}
