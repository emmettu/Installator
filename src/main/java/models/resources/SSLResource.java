package models.resources;

import models.panels.SSLModel;
import models.resources.exceptions.CommandFailedException;
import models.resources.servers.ServerResource;
import org.jboss.dmr.ModelNode;

/**
 * Created by eunderhi on 14/04/16.
 * Resource that, when given an SSL Model, can run the required server commands
 * to install it properly.
 */
public class SSLResource {

    private ServerResource server;

    public SSLResource(ServerResource server) {
        this.server = server;
    }

    public void installSSL(SSLModel model) throws CommandFailedException {
        model.vaultPassword();
        String addSSLCommand =
                "/core-service=management/security-realm=" +
                model.getRealm() +
                "/server-identity=ssl:add(keystore-path=\"" +
                model.getKeyStoreLocation() +
                "\",keystore-password=\"" +
                model.getPassword() +
                "\")";
        String addTrustStoreCommand =
                "/core-service=management/security-realm=" +
                model.getRealm() +
                "/authentication=truststore:add(keystore-path=\"" +
                model.getKeyStoreLocation() +
                "\",keystore-password=\"" +
                model.getPassword() +
                "\")";

        server.submit(addSSLCommand);
        server.submit(addTrustStoreCommand);
    }

    public void addHttps() throws CommandFailedException {
        String redefine = "/core-service=management/management-interface=http-interface:undefine-attribute(name=security-realm)";
        String addCommand = "/core-service=management/management-interface=http-interface:write-attribute(name=security-realm,value=\"ManagementRealm\")";
        server.submit(redefine);
        server.submit(addCommand);

        String undefineHttp = "/core-service=management/management-interface=http-interface:undefine-attribute(name=socket-binding";
        String defineHttps = "/core-service=management/management-interface=http-interface:write-attribute(name=secure-socket-binding,value=management-https)";
        ModelNode check = server.getModelNodeResult("/core-service=management:read-children-names(child-type=management-interface)");
        for (ModelNode c : check.get("result").asList()) {
            if (c.asString().equals("http-interface")) {
                server.submit(undefineHttp);
                server.submit(defineHttps);
            }
        }
    }

}
