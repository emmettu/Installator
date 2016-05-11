package models.panels.securitydomain.ports;

import models.resources.exceptions.CommandFailedException;

/**
 * Created by eunderhi on 11/05/16.
 */
public interface PortWriter {

    void writePort(Port port) throws CommandFailedException;

}
