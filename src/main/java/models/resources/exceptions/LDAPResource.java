package models.resources.exceptions;

import models.panels.LdapModel;
import models.resources.servers.ServerResource;

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
    }

    public synchronized void createLdapConnection(LdapModel model) throws CommandFailedException {
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

}
