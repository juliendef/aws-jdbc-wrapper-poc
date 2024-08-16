package org.example;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Config {

    private static volatile Config instance;

    private String url;
    private String username;
    private String password;

    private Config() {
        this.url = getProperty("db_url");
        this.username = getProperty("db_username");
        this.password = getProperty("db_password");
    }

    private String getProperty(String key) {
        String value;
        try {
            Properties properties = new Properties();
            try (InputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
                properties.load(inputStream);
                value = properties.getProperty(key);
            }
        } catch (IOException e) {
            value = System.getenv(key);
        }

        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found");
        }

        return value;
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }
}
