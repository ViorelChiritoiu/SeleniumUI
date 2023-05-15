package org.selenium.pom.constants;

import java.util.Map;

public class Codes {

    public static String getcountryCode(String countryName) {
        Map<String, String> countryMap = Map.of("India", "IN", "United States", "US");
        return countryMap.get(countryName);
    }

    public static String getStateCode(String stateName) {
        Map<String, String> stateMap = Map.of("Maharashtra", "MH", "Texas", "TX");
        return stateMap.get(stateName);
    }
}
