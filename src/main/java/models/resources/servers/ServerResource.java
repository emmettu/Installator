package models.resources.servers;

import models.resources.exceptions.CommandFailedException;
import org.jboss.as.cli.CliInitializationException;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;

/**
 * Created by eunderhi on 05/04/16.
 * The resource responsible for executing server commands
 */
public class ServerResource {

    private CommandContext cc;
    private String startCommand;

    ServerResource() {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        cc = createCommandContext();
    }

    private CommandContext createCommandContext() {
        CommandContext cc = null;
        try {
            cc = CommandContextFactory.getInstance().newCommandContext();
        }
        catch (CliInitializationException e) {
            e.printStackTrace();
        }
        return cc;
    }

    void setupCommandContext() {
        cc.setSilent(true);
        try {
            cc.handle(startCommand);
        } catch (CommandLineException e) {
            e.printStackTrace();
        }
    }

    public synchronized void submit(String command) throws CommandFailedException {
        try {
            cc.handle(command);
        }
        catch (CommandLineException e) {
            throw new CommandFailedException(e.getMessage());
        }
    }

    public synchronized void shutDown() {
        cc.terminateSession();
    }

    void setStartCommand(String command) {
        startCommand = command;
    }

}
