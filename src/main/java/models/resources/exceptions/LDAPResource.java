package models.resources.exceptions;

import models.panels.LdapModel;
import models.panels.SSLModel;
import models.resources.SSLResource;
import models.resources.servers.ServerResource;
import org.jboss.as.controller.client.helpers.Operations;
import org.jboss.dmr.ModelNode;

/**
 * Created by eunderhi on 02/05/16.
 * Resource that installs LDAP items
 */
public class LDAPResource {

    private ServerResource server;

    public LDAPResource(ServerResource server) {
        this.server = server;
    }

    public synchronized void installLdap(LdapModel model) throws CommandFailedException {
        createLdapConnection(model);
        createLdapSecurityRealm(model);
        installLdapToInterfaces(model);
        installSSL(model);
    }

    private synchronized void createLdapConnection(LdapModel model) throws CommandFailedException {
        String addLdapConnCmd =
                        "/core-service=management/ldap-connection=" +
                        model.getName() +
                        "/:add(search-credential=\"" +
                        model.getPassword() +
                        "\",url=\"" +
                        model.getUrl() +
                        "\",search-dn=\"" +
                        model.getdN() +
                        "\")";
        server.submit(addLdapConnCmd);
    }

    private void createLdapSecurityRealm(LdapModel model) throws CommandFailedException {
        String createLdapSecRealmCmd =
                "/core-service=management/security-realm=\"" +
                model.getRealmName() +
                "\":add";

        String addLdapSecRealmCmd =
                "/core-service=management/security-realm=\"" +
                model.getRealmName() +
                "\"/authentication=ldap:add(base-dn=\"" +
                model.getBaseDN() +
                "\", recursive=\"" +
                model.isRecursive() +
                "\", connection=\"" +
                model.getName() +
                "\"";

        addLdapSecRealmCmd += model.getFilterType().getCommand() + model.getFilter() + "\")";

        server.submit(createLdapSecRealmCmd);
        server.submit(addLdapSecRealmCmd);
    }

    private void installLdapToInterfaces(LdapModel model) throws CommandFailedException {
        writeSecurityRealmAttribute("http-interface", model.getRealmName());
        writeSecurityRealmAttribute("native-interface", model.getRealmName());
    }

    private void writeSecurityRealmAttribute(String interfaceName, String realmName) throws CommandFailedException {
        String checkCmd = "/core-service=management:read-children-names(child-type=management-interface)";
        ModelNode check = server.getModelNodeResult(checkCmd);
        if (Operations.isSuccessfulOutcome(check)) {
            for (ModelNode c : check.get("result").asList()) {
                // only write the security-realm attribute if the management-interface exists.
                if (c.asString().equals(interfaceName)) {
                    String writeSecurityRealmCmd =
                            "/core-service=management/management-interface=" +
                             interfaceName +
                             "/:" +
                             "write-attribute(name=security-realm" +
                             ",value=" +
                             realmName +
                             ")";

                    server.submit(writeSecurityRealmCmd);
                }
            }
        }
    }

    private void installSSL(LdapModel model) throws CommandFailedException {
        if (model.getSSL().isPresent()) {
            SSLModel sslModel = model.getSSL().get();
            sslModel.setRealm(model.getRealmName());
            new SSLResource(server).installSSL(sslModel);
        }
    }

}
