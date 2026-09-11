package com.saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not read config.properties file.", e);
        }
    }

    public static String getProperty(String key) {

        String systemProperty = System.getProperty(key);

        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            return systemProperty.trim();
        }

        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Property key '" + key + "' not found in config.properties"
            );
        }

        return value.trim();
    }

    public static int getIntProperty(String key) {
        String value = getProperty(key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Property '" + key +
                    "' must be a valid integer. Current value: " +
                    value,
                    e
            );
        }
    }
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);

        if (!value.equalsIgnoreCase("true") &&
            !value.equalsIgnoreCase("false")) {

            throw new RuntimeException(
                    "Property '" + key +
                    "' must be either true or false. Current value: " +
                    value
            );
        }

        return Boolean.parseBoolean(value);
    }
}