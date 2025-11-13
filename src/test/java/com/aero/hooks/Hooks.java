package com.aero.hooks;

import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

  @Inject
  private GuiceScoped guiceScoped;

  @After
  public void afterScenario() {
    WebDriver driver = guiceScoped.getDriver();
    if (driver != null) {
      driver.quit();
    }
  }
}
