package com.aero.modules;

import com.aero.components.HeaderComponent;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class HeaderGuiceModul extends AbstractModule {
  private final WebDriver driver;

  public HeaderGuiceModul(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public HeaderComponent getHeaderComponent() {
    return new HeaderComponent(driver);
  }
}
