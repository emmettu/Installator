package models.resources;

import models.resources.exceptions.CommandFailedException;
import org.jboss.as.cli.CliInitializationException;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;

/**
 * Created by eunderhi on 05/04/16.
 */
public class ServerResource {

    private CommandContext cc;

    public ServerResource(String jbossHome, String serverFile) {
        cc = createCommandContext();
        setupCommandContext(jbossHome, serverFile);
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

    private void setupCommandContext(String home, String serverFile) {
        cc.setSilent(true);
        try {
            cc.handle(String.format("embed-host-controller --jboss-home=%s --host-config=%s", home, serverFile));
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

}
