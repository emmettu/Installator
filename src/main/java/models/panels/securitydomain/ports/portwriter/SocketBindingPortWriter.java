package models.panels.securitydomain.ports.portwriter;

import models.panels.securitydomain.ports.Port;
import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

/**
 * Created by eunderhi on 11/05/16.
 */
public class SocketBindingPortWriter implements PortWriter {

    private ServerResource server;
    private String socketBindingGroup;
    private String socketBinding;
    private String attribute;

    public static Builder Builder() {
        return new Builder();
    }

    private SocketBindingPortWriter(Builder builder) {
        builder.server = server;
        builder.socketBindingGroup = socketBindingGroup;
        builder.socketBinding = socketBinding;
        builder.attribute = attribute;
    }

    @Override
    public void writePort(Port port) throws CommandFailedException {
        server.submit("/socket-binding-group=" +
                socketBindingGroup +
                "/socket-binding=" +
                socketBinding +
                ":write-attribute(name=" +
                attribute +
                ",value=" +
                port +
                ")"
        );
    }

    public static class Builder {
        private ServerResource server;
        private String socketBindingGroup;
        private String socketBinding;
        private String attribute;

        private Builder() {}

        public Builder setServer(ServerResource server) {
            this.server = server;
            return this;
        }

        public Builder setSocketBindingGroup(String socketBindingGroup) {
            this.socketBindingGroup = socketBindingGroup;
            return this;
        }

        public Builder setSocketBinding(String socketBinding) {
            this.socketBinding = socketBinding;
            return this;
        }

        public Builder setAttribute(String attribute) {
            this.attribute = attribute;
            return this;
        }

        public SocketBindingPortWriter create() {
            return new SocketBindingPortWriter(this);
        }
    }

}
