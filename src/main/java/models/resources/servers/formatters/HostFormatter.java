package models.resources.servers.formatters;

/**
 * Created by eunderhi on 19/04/16.
 */
public class HostFormatter implements CommandFormatter {

    @Override
    public String format(String command) {
        if (isCoreService(command)) {
            return "/host=master" + command;
        }
        return command;
    }

    private boolean isCoreService(String command) {
        return command.startsWith("/core-service");
    }

}
