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

    public PortWriter httpStandalonePortWriter() {
        return SocketBindingPortWriter.Builder()
                .setServer(server)
                .setAttribute("port")
                .setSocketBinding("http")
                .setSocketBindingGroup("standard-sockets")
                .create();
    }

    public PortWriter httpsStandalonePortWriter() {
        return SocketBindingPortWriter.Builder()
                .setServer(server)
                .setAttribute("port")
                .setSocketBinding("https")
                .setSocketBindingGroup("standard-sockets")
                .create();
    }

    public PortWriter txnRecoveryEnvironmentStandalonePortWriter() {
        return SocketBindingPortWriter.Builder()
                .setServer(server)
                .setAttribute("port")
                .setSocketBinding("txn-recovery-environment")
                .setSocketBindingGroup("standard-sockets")
                .create();
    }

    public PortWriter txnStatusManagerStandalonePortWriter() {
        return SocketBindingPortWriter.Builder()
                .setServer(server)
                .setAttribute("port")
                .setSocketBinding("txn-status-manager")
                .setSocketBindingGroup("standard-sockets")
                .create();
    }

}
