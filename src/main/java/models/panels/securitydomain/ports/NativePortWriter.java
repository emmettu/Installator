package models.panels.securitydomain.ports;

import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 11/05/16.
 */
public class NativePortWriter implements PortWriter {

    private ServerResource server;

    public NativePortWriter(ServerResource sr) {
        server = sr;
    }

    @Override
    public void writePort(Port port) throws CommandFailedException {
        String command = "/core-service=management/management-interface=native-interface:write-attribute(name=port,value="+port+")";
        server.submit(command);
    }

}
