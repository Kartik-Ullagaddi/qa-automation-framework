package com.kartik.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class UserReader {

    private static Map<String, User> users;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = UserReader.class.getClassLoader().getResourceAsStream("users.json");
            if (is != null) {
                users = mapper.readValue(is, mapper.getTypeFactory().constructMapType(Map.class, String.class, User.class));
                is.close();
            } else {
                throw new RuntimeException("users.json file not found on classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users.json file", e);
        }
    }

    public static User getUser(String userType) {
        User user = users.get(userType);
        if (user == null) {
            throw new RuntimeException("User type '" + userType + "' not found in users.json");
        }
        return user;
    }
}