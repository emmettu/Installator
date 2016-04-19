package models.resources.servers.formatters;

/**
 * Created by eunderhi on 19/04/16.
 */
public class DefaultFormatter implements CommandFormatter {

    @Override
    public String format(String command) {
        return command;
    }

}
