package com.aero.jupiter.extensions;

import com.aero.factory.WebDriverFactory;
import com.aero.listener.HighlightListener;
import com.aero.modules.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class UiExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector;
  private WebDriver driver;

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver baseDriver = new WebDriverFactory().getDriver();

    driver = new EventFiringDecorator(new HighlightListener()).decorate(baseDriver);

    injector = Guice.createInjector(
            new GuicePagesModule(driver),
            new GuiceComponentsModule(driver)
    );

    injector.injectMembers(context.getTestInstance().get());
  }

  @Override
  public void afterEach(ExtensionContext context) {
    if (driver != null) {
      driver.quit();
    }
  }
}
