package models.resources.servers.formatters;

/**
 * Created by eunderhi on 19/04/16.
 * Formatter that takes regular commands and formats them to be submitted
 * to a host-controller server resource.
 */
public class HostFormatter implements CommandFormatter {

    private static final String[] DOMAIN_PROFILES = {"default", "full", "full-ha", "ha"};

    @Override
    public String format(String command) {
        if (isCoreService(command)) {
            return "/host=master" + command;
        }
        if (isSubsystem(command)) {
            return formatSubsystem(command);
        }
        return command;
    }

    private boolean isCoreService(String command) {
        return command.startsWith("/core-service");
    }

    private boolean isSubsystem(String command) {
        return command.startsWith("/subsystem");
    }

    private String formatSubsystem(String command) {
        String fullCommand = "";
        for (String profile : DOMAIN_PROFILES) {
            fullCommand += "/profile=" + profile + command + ";";
        }
        return fullCommand;
    }

}
