package avrotools;

import java.util.Map;

public interface BaseUser {
    String getUsername();

    String getPassword();

    double getAge();

    Map<Integer, String> getProperties();

    Map<Integer, Map<Double, String>> getNestedProperties();

    Map<String, Integer> getStringMap();

    Map<Integer, Map<String, Double>> getNestedStringMap();
}
