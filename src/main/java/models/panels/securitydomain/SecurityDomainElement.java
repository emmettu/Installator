package models.panels.securitydomain;

import java.util.Map;

/**
 * Created by eunderhi on 06/05/16.
 * Represents a security domain element
 */
public interface SecurityDomainElement {

    SecurityDomainCode getCode();
    SecurityDomainFlag getFlag();
    Map<String, String> getOptions();

}
