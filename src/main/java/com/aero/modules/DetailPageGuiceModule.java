package com.aero.modules;

import com.aero.pages.CourseDetailPage;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class DetailPageGuiceModule extends AbstractModule {
  WebDriver driver;

  public DetailPageGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public CourseDetailPage getDetailPage() {
    return new CourseDetailPage(driver);
  }
}
