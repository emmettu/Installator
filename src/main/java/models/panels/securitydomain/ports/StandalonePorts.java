package models.panels.securitydomain.ports;

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
    private Port ajp = Port.create().withName("jboss.ajp.port").withValue(1);
    private Port http = Port.create().withName("jboss.http.port").withValue(2);
    private Port https = Port.create().withName("jboss.https.port").withValue(3);
    private Port txnRecovery = Port.create().withValue(4);
    private Port txnStatus = Port.create().withValue(5);
    private Port managementHttp = Port.create().withName("jboss.management.http.port").withValue(9990);
    private Port managementHttps = Port.create().withName("jboss.management.https.port").withValue(9993);
    private PortWriterFactory pwf;

    public StandalonePorts(ServerResource server) {
        pwf = new PortWriterFactory(server);
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void writePorts() throws CommandFailedException {
        pwf.ajpStandalonePortWriter().writePort(ajp.withOffset(offset));
        pwf.httpStandalonePortWriter().writePort(http.withOffset(offset));
        pwf.httpsStandalonePortWriter().writePort(https.withOffset(offset));
        pwf.txnRecoveryEnvironmentStandalonePortWriter().writePort(txnRecovery.withOffset(offset));
        pwf.txnStatusManagerStandalonePortWriter().writePort(txnStatus.withOffset(offset));
    }

}
