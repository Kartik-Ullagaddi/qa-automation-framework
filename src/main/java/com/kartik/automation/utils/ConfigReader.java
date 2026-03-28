package com.kartik.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    // Static block to load properties file
    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            // Load the properties file from the classpath
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
