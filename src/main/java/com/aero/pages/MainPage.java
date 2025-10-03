package com.aero.pages;

import com.aero.components.HeaderComponent;
import com.aero.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    HeaderComponent headerComponent;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
        headerComponent = new HeaderComponent(driver);
    }

    public MainPage open(){
        driver.get(PropertyLoader.getBaseUrl()+"/");
        return this;
    }

    public HeaderComponent header() {
        return headerComponent;
    }
}
