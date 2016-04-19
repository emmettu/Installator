package models.resources.servers.formatters;

/**
 * Created by eunderhi on 19/04/16.
 * Performs some sort of formatting on a given command.
 */
public interface CommandFormatter {

    String format(String command);

}
