package models.panels.securitydomain.ports.portwriter;

import models.panels.securitydomain.ports.Port;
import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 12/05/16.
 */
public class ManagementPortWriter implements PortWriter {

    private String realm;
    private ServerResource server;

    ManagementPortWriter(String realm, ServerResource server) {
        this.realm = realm;
        this.server = server;
    }

    @Override
    public void writePort(Port port) throws CommandFailedException {
        String command =
                "/core-service=management/management-interface=" +
                realm +
                ":write-attribute(name=port,value=" +
                port +
                ")";

        server.submit(command);
    }

}
