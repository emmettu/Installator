package models.panels.securitydomain;

/**
 * Created by eunderhi on 06/05/16.
 */
public enum AuthenticationCode implements SecurityDomainCode {

            Client,
            Certificate,
            CertificateUsers,
            CertificateRoles,
            Database,
            DatabaseCertificate,
            DatabaseUsers,
            Identity,
            Ldap,
            LdapExtended,
            RoleMapping,
            RunAs,
            Simple,
            ConfiguredIdentity,
            SecureIdentity,
            PropertiesUsers,
            SimpleUsers,
            LdapUsers,
            Kerberos,
            SPNEGOUsers,
            AdvancedLdap,
            AdvancedADLdap,
            UsersRoles;

    private String code;

    AuthenticationCode() {
        this.code = this.toString();
    }

    @Override
    public String getCode() {
        return code;
    }

}
