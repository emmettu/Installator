package models.panels.securitydomain.ports.portwriter;

import models.panels.securitydomain.ports.Port;
import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 11/05/16.
 */
public class HttpPortWriter implements PortWriter {

    private ManagementPortWriter writer;

    public HttpPortWriter(ServerResource sr) {
        writer = new ManagementPortWriter("http-interface", sr);
    }

    @Override
    public void writePort(Port port) throws CommandFailedException {
        writer.writePort(port);
    }

}
