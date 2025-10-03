package com.aero.modules;

import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class MainPageGuiceModule extends AbstractModule {
    WebDriver driver;

    public MainPageGuiceModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }
}
