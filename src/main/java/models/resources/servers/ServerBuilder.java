package models.resources.servers;

import models.packaging.InstallLocationModel;
import models.resources.servers.formatters.HostFormatter;

/**
 * Created by eunderhi on 18/04/16.
 * Factory for wildfly server types
 */
public class ServerBuilder {

    private InstallLocationModel ilm;

    public ServerBuilder(InstallLocationModel ilm) {
        this.ilm = ilm;
    }

    public ServerResource newHostServer(String hostFile) {
        ServerResource sr = new ServerResource();
        sr.setStartCommand("embed-host-controller --jboss-home=" +
                ilm.getInstallLocation() +
                " --host-config=" +
                hostFile);
        sr.setupCommandContext();
        sr.setFormatter(new HostFormatter());
        return sr;
    }

    public ServerResource newStandaloneServer(String hostFile) {
        ServerResource sr = new ServerResource();
        sr.setStartCommand("embed-server --jboss-home=" +
                ilm.getInstallLocation() +
                " --server-config=" +
                hostFile);
        sr.setupCommandContext();
        return sr;
    }

}
