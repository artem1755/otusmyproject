package com.aero.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
  private static final Properties PROPERTIES = new Properties();

  // Статический блок загружает проперти при первом обращении к классу
  static {
    try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (input == null) {
        throw new RuntimeException("Не найден файл config.properties в classpath");
      }
      PROPERTIES.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Ошибка при загрузке config.properties", e);
    }
  }

  private PropertyLoader() {
  }

  public static String getBaseUrl() {
    return PROPERTIES.getProperty("base.url");
  }

  public static int getBaseTimeout() {
    String timeout = PROPERTIES.getProperty("base.timeout");
    if (timeout == null) {
      throw new RuntimeException("base.timeout не задан в config.properties");
    }
    return Integer.parseInt(timeout);
  }

  public static String getBrowserName() {
    return PROPERTIES.getProperty("browser.name");
  }
}
