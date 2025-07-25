package com.elpais.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Could not load config: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
    public static int getInt(String key) { return Integer.parseInt(props.getProperty(key));}
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }
}
