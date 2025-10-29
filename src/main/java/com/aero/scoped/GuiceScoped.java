package com.aero.scoped;

import com.aero.factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  public WebDriver driver = new WebDriverFactory().getDriver();

  public WebDriver getDriver() {
    return driver;
  }
}
