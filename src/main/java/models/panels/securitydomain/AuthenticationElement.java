package models.panels.securitydomain;

import java.util.Map;

/**
 * Created by eunderhi on 06/05/16.
 */
public class AuthenticationElement {

    private AuthenticationCode code;
    private AuthenticationFlag flag;
    private Map<String, String> options;

    public AuthenticationCode getCode() {
        return code;
    }

    public AuthenticationFlag getFlag() {
        return flag;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setCode(AuthenticationCode code) {
        this.code = code;
    }

    public void setFlag(AuthenticationFlag flag) {
        this.flag = flag;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

}
