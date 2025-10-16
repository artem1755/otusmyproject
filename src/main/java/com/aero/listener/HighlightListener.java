package com.aero.listener;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

  @Override
  public void beforeClick(WebElement element) {
    if (element instanceof WrapsDriver) {
      WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
      highlightElement(driver, element);
    } else {
      throw new IllegalArgumentException("Element does not implement WrapsDriver: " + element.getClass().getName());
    }
  }

  private void highlightElement(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'", element);
  }
}
