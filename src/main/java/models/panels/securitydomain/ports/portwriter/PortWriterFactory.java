package models.panels.securitydomain.ports.portwriter;

import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 12/05/16.
 */
public class PortWriterFactory {

    private ServerResource server;

    public PortWriterFactory(ServerResource server) {
        this.server = server;
    }

    public PortWriter ajpStandalonePortWriter() {
        return SocketBindingPortWriter.Builder()
                .setServer(server)
                .setAttribute("port")
                .setSocketBinding("ajp")
                .setSocketBindingGroup("standard-sockets")
                .create();
    }

}
