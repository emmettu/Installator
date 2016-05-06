package models.panels.securitydomain;

/**
 * Created by eunderhi on 06/05/16.
 */
public enum AuthorizationCode implements SecurityDomainCode {

    DenyAll,
    PermitAll,
    Delegating,
    Web,
    JACC,
    XACML;

    @Override
    public String getCode() {
        return toString();
    }

}
