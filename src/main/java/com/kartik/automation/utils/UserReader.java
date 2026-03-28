package com.kartik.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UserReader {

    private static Map<String, User> users;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            users = mapper.readValue(
                    new File("src/test/resources/users.json"),
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, User.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String userType) {
        return users.get(userType);
    }
}