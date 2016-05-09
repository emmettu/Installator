package models.panels.securitydomain;

import java.util.List;

/**
 * Created by eunderhi on 06/05/16.
 */
public class SecurityDomainModel {

    private String name;
    private CacheType cacheType;
    private List<AuthenticationElement> authenticationElements;
    private List<AuthorizationElement> authorizationElements;
    private List<MappingElement> mappingElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

}
