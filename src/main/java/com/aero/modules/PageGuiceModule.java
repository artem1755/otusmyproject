package com.aero.modules;

import com.aero.pages.CoursesPage;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class PageGuiceModule extends AbstractModule {
    WebDriver driver;

    public PageGuiceModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public CoursesPage getCoursesPage() {
        return new CoursesPage(driver);
    }
}
