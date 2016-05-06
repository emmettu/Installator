package models.panels.securitydomain;

import java.util.Map;

/**
 * Created by eunderhi on 06/05/16.
 */
public class AuthenticationElement implements SecurityDomainElement {

    private AuthenticationCode code;
    private AuthenticationFlag flag;
    private Map<String, String> options;

    @Override
    public AuthenticationCode getCode() {
        return code;
    }

    @Override
    public AuthenticationFlag getFlag() {
        return flag;
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }
}
