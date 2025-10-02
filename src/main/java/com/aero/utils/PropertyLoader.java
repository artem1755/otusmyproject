package com.aero.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final Properties properties = new Properties();

    // Статический блок загружает проперти при первом обращении к классу
    static {
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не найден файл config.properties в classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }
    }

    private PropertyLoader() {}

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static int getBaseTimeout() {
        String timeout = properties.getProperty("base.timeout");
        if (timeout == null) {
            throw new RuntimeException("base.timeout не задан в config.properties");
        }
        return Integer.parseInt(timeout);
    }
}
