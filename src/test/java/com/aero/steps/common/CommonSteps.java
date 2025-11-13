package com.aero.steps.common;

import com.aero.factory.WebDriverFactory;
import com.aero.scoped.GuiceScoped;
import com.aero.utils.PropertyLoader;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebDriver;

public class CommonSteps {
  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  private WebDriverFactory driverFactory;

  @Пусть("Я открываю браузер {string}")
  public void openBrowser(String browserName) {
    WebDriver driver = driverFactory.createDriver(browserName);
    guiceScoped.setDriver(driver);
    driver.manage().window().maximize();
    driver.get(PropertyLoader.getBaseUrl());
  }
}
