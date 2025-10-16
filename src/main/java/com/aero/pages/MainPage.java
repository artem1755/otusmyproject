package com.aero.pages;

import com.aero.components.HeaderComponent;
import com.aero.utils.PropertyLoader;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
  private final WebDriver driver;
  private final WebDriverWait wait;
  @Inject
  HeaderComponent headerComponent;

  public MainPage(WebDriver driver, HeaderComponent headerComponent) {
    this.driver = driver;
    this.headerComponent = headerComponent;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
    PageFactory.initElements(driver, this);
  }

  public MainPage open() {
    driver.get(PropertyLoader.getBaseUrl() + "/");
    return this;
  }

  public HeaderComponent header() {
    return headerComponent;
  }
}
