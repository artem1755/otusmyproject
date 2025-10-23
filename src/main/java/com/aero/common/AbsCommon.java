package com.aero.common;

import com.aero.utils.UiActions;
import com.aero.waiters.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsCommon<T extends AbsCommon> {
  protected WebDriver driver;
  protected Waiter waiter;
  protected UiActions uiActions;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.waiter = new Waiter(driver).getWaitDriver();
    this.uiActions = new UiActions(driver);
    PageFactory.initElements(driver, this);
  }

}
