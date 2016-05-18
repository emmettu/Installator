package models.panels.logging;

/**
 * Created by eunderhi on 18/05/16.
 */
public class LoggingModel {

    private LoggingLevel root = LoggingLevel.INFO;
    private LoggingLevel console = LoggingLevel.INFO;

    public LoggingLevel getRoot() {
        return root;
    }

    public void setRoot(LoggingLevel root) {
        this.root = root;
    }

    public LoggingLevel getConsole() {
        return console;
    }

    public void setConsole(LoggingLevel console) {
        this.console = console;
    }

}
