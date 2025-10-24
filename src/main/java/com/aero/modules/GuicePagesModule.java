package com.aero.modules;

import com.aero.components.HeaderComponent;
import com.aero.pages.CourseDetailPage;
import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class GuicePagesModule extends AbstractModule {
  private WebDriver driver;

  public GuicePagesModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  public WebDriver getWebDriver() {
    return driver;
  }

  @Provides
  @Singleton
  public MainPage getMainPage(HeaderComponent headerComponent) {
    return new MainPage(driver, headerComponent);
  }

  @Provides
  @Singleton
  public CourseDetailPage getDetailPage() {
    return new CourseDetailPage(driver);
  }

  @Provides
  @Singleton
  public CoursesPage getCoursesPage() {
    return new CoursesPage(driver);
  }
}
