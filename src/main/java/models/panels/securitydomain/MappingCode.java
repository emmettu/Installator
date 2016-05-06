package models.panels.securitydomain;

/**
 * Created by eunderhi on 06/05/16.
 */
public enum MappingCode implements SecurityDomainCode {

    PropertiesRoles,
    SimpleRoles,
    DeploymentRoles,
    DatabaseRoles,
    LdapRoles;

    @Override
    public String getCode() {
        return toString();
    }

}
