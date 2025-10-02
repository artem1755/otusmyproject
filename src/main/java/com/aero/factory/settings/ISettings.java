package com.aero.factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface ISettings {
    AbstractDriverOptions getSettings(DesiredCapabilities desiredCapabilities);
}
