package models.resources.servers;

import models.resources.exceptions.CommandFailedException;
import models.resources.servers.formatters.CommandFormatter;
import models.resources.servers.formatters.DefaultFormatter;
import org.jboss.as.cli.*;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

/**
 * Created by eunderhi on 05/04/16.
 * The resource responsible for executing server commands
 */
public class ServerResource {

    private CommandContext cc;
    private String startCommand;
    private CommandFormatter formatter = new DefaultFormatter();

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
        command = formatter.format(command);
        try {
            cc.handle(command);
        }
        catch (CommandLineException e) {
            throw new CommandFailedException(e.getMessage());
        }
    }

    public synchronized ModelNode getModelNodeResult(String command) throws CommandFailedException {
        ModelControllerClient mcc = cc.getModelControllerClient();
        try {
            return mcc.execute(cc.buildRequest(command));
        } catch (Exception e) {
            throw new CommandFailedException(e.getMessage());
        }
    }

    void setFormatter(CommandFormatter formatter) {
        this.formatter = formatter;
    }

    public synchronized void shutDown() {
        cc.terminateSession();
    }

    void setStartCommand(String command) {
        startCommand = command;
    }

}
