package com.aero.factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class
ChromeSettings implements ISettings{
    @Override
    public AbstractDriverOptions getSettings(DesiredCapabilities desiredCapabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(desiredCapabilities);
//        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--start-maximized");
        return chromeOptions;
    }
}
