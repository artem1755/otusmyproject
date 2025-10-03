package com.aero.factory;

import com.aero.exceptions.BrowserNotSupportException;
import com.aero.factory.settings.ChromeSettings;
import com.aero.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    private String browserName = PropertyLoader.getBrowserName();

    public WebDriver getDriver(){
        switch (browserName){
            case "chrome":
                return new ChromeDriver((ChromeOptions) new ChromeSettings().getSettings(new DesiredCapabilities()));
        }
        throw new BrowserNotSupportException();
    }
}
