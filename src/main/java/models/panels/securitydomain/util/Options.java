package models.panels.securitydomain.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eunderhi on 09/05/16.
 * A utility class for parsing option maps out of strings
 */
public class Options {

    private static final String ON_COMMA = "[,](?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final String ON_EQUAL = "[=](?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static Map<String, String> fromString(String s) {
        Map<String, String> optionMap = new HashMap<>();
        String[] options = s.split(ON_COMMA);

        for (String option : options) {
            String[] keyAndValue = option.split(ON_EQUAL);
            String key = keyAndValue[0];
            String value = keyAndValue[1];
            value = cleanUp(value);
            optionMap.put(key, value);
        }
        return optionMap;
    }

    private static String cleanUp(String value) {
        return value.replaceAll("\"", "");
    }

}
