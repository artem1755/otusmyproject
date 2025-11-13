package com.aero.factory;

import com.aero.factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Locale;

public class WebDriverFactory {

  public WebDriverFactory() {}

  public WebDriver createDriver(String browserName) {
    return switch (browserName.toLowerCase(Locale.ENGLISH)) {
      case "chrome" -> {
        ChromeOptions options = (ChromeOptions) new ChromeSettings().getSettings(new DesiredCapabilities());
        yield new ChromeDriver(options);
      }
      case "firefox" -> {
        yield new FirefoxDriver();
      }
      case "edge" -> {
        EdgeOptions options = new EdgeOptions();
        yield new EdgeDriver(options);
      }
      default -> throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
    };
  }
}
