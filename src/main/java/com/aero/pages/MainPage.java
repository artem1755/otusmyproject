package com.aero.pages;

import com.aero.annotations.Path;
import com.aero.components.HeaderComponent;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  @Inject
  HeaderComponent headerComponent;

  public MainPage(WebDriver driver, HeaderComponent headerComponent) {
    super(driver);
    this.headerComponent = headerComponent;
  }

  public HeaderComponent header() {
    return headerComponent;
  }
}
