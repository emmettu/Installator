package models.panels.securitydomain.ports;

import models.panels.securitydomain.ports.portwriter.PortWriter;
import models.panels.securitydomain.ports.portwriter.PortWriterFactory;
import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 12/05/16.
 */
public class StandalonePorts {

    private List<Port> ports = new ArrayList<>();
    private int offset;
    private ServerResource server;

    public StandalonePorts(ServerResource server) {
        ports.add(Port.create().withName("jboss.ajp.port").withValue(8123));
        ports.add(Port.create().withName("jboss.http.port").withValue(8080));
        ports.add(Port.create().withName("jboss.https.port").withValue(8443));
        ports.add(Port.create().withName("jboss.management.http.port").withValue(9990));
        ports.add(Port.create().withName("jboss.management.https.port").withValue(9993));
        ports.add(Port.create().withName("jboss.management.native.port").withValue(9999));
        this.server = server;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void writePorts() throws CommandFailedException {
        //ports.forEach(p -> p.withOffset(offset));
        PortWriter pw = new PortWriterFactory(server).ajpStandalonePortWriter();
        pw.writePort(ports.get(0));
    }

}
