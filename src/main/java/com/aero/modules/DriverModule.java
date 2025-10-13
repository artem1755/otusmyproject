package com.aero.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {
  private final WebDriver driver;

  public DriverModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  WebDriver provideWebDriver() {
    return driver;
  }
}
