package models.panels.securitydomain;

import java.util.Map;

/**
 * Created by eunderhi on 06/05/16.
 */
public class MappingElement {

    private MappingCode code;
    private MappingType mapType;
    private Map<String, String> options;

    public MappingCode getCode() {
        return code;
    }

    public MappingType getMapType() {
        return mapType;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setCode(MappingCode code) {
        this.code = code;
    }

    public void setMapType(MappingType mapType) {
        this.mapType = mapType;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

}
