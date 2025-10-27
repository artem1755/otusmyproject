package com.aero.waiters;

import com.aero.utils.PropertyLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waiter {
  private final WebDriver driver;
  private final int waiterTimeout = PropertyLoader.getBaseTimeout();
  WebDriverWait wait;

  public Waiter(WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition<?> expectedCondition) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(waiterTimeout))
              .until(expectedCondition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
  }

  public Waiter getWaitDriver() {
    wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
    return this;
  }

  public boolean waitForElementVisible(By locator) {
    return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public boolean waitForElementNotVisible(By locator) {
    return waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public boolean waitForElementClickable(WebElement element) {
    return this.waitForCondition(ExpectedConditions.elementToBeClickable(element));
  }

}
