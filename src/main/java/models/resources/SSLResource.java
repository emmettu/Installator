package models.resources;

import models.panels.SSLModel;
import models.resources.exceptions.CommandFailedException;

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
        String realm = "ManagementRealm";
        String addSSLCommand =
                "/core-service=management/security-realm=" +
                realm +
                "/server-identity=ssl:add(keystore-path=\"" +
                model.getKeyStoreLocation() +
                "\",keystore-password=\"" +
                model.getPassword() +
                "\")";
        String addTrustStoreCommand =
                "/core-service=management/security-realm=" +
                realm +
                "/authentication=truststore:add(keystore-path=\"" +
                model.getKeyStoreLocation() +
                "\",keystore-password=\"" +
                model.getPassword() +
                "\")";

        server.submit(addSSLCommand);
        server.submit(addTrustStoreCommand);
    }

}
