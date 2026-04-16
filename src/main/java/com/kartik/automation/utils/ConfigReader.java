package com.kartik.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    // Static block to load properties file
    static {
        try {
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties = new Properties();
            if (input != null) {
                properties.load(input);
                input.close();
            } else {
                throw new RuntimeException("config.properties file not found on classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
