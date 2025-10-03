package com.aero.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UiActions {

    private UiActions() {}

    public static void click(WebDriver driver, WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .click()
                .perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .doubleClick()
                .perform();
    }

    public static void hover(WebDriver driver, WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }
}
