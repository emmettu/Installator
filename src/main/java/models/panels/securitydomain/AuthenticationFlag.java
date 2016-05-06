package models.panels.securitydomain;

/**
 * Created by eunderhi on 06/05/16.
 */
public enum AuthenticationFlag implements SecurityDomainFlag {

    Required,
    Requisite,
    Sufficient,
    Optional;

    private String flag;

    AuthenticationFlag() {
        flag = this.toString();
    }

    @Override
    public String getFlag() {
        return flag;
    }

}
