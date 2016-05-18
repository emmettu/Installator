package models.panels.logging;

import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 18/05/16.
 */
public class LoggingResource {

    private ServerResource server;

    public LoggingResource(ServerResource server) {
        this.server = server;
    }

    public void installLogging(LoggingModel model) throws CommandFailedException {
        server.submit(getRootCommand(model.getRoot()));
        server.submit(getConsoleCommand(model.getConsole()));
    }

    private String getRootCommand(LoggingLevel level) {
        return String.format(
                "/subsystem=logging/%s=%s:write-attribute(name=level,value=%s)", "root-logger", "ROOT", level
        );
    }

    private String getConsoleCommand(LoggingLevel level) {
        return String.format(
                "/subsystem=logging/%s=%s:write-attribute(name=level,value=%s)", "console-handler", "CONSOLE", level
        );
    }

}
