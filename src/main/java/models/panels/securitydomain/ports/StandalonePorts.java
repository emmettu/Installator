package models.panels.securitydomain.ports;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 12/05/16.
 */
public class StandalonePorts {

    private List<Port> ports = new ArrayList<>();
    private int offset;

    public StandalonePorts() {
        ports.add(Port.create().withName("jboss.ajp.port").withValue(8009));
        ports.add(Port.create().withName("jboss.http.port").withValue(8080));
        ports.add(Port.create().withName("jboss.https.port").withValue(8443));
        ports.add(Port.create().withName("jboss.management.http.port").withValue(9990));
        ports.add(Port.create().withName("jboss.management.https.port").withValue(9993));
        ports.add(Port.create().withName("jboss.management.native.port").withValue(9999));
        ports.add(Port.create().withName("jboss.management.native.port").withValue(9999));
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void writePorts() {
        ports.forEach(p -> p.withOffset(offset));
    }

}
