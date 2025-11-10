package com.aero.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UiActions {
  private Actions actions;

  public UiActions(WebDriver driver) {
    this.actions = new Actions(driver);
  }

  public void hoverOnElement(WebElement element) {
    actions.moveToElement(element)
            .build()
            .perform();
  }

  public void clickOnElement(WebElement element) {
    actions.moveToElement(element)
            .click()
            .build()
            .perform();
  }
}
