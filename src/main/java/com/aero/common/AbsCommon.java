package com.aero.common;

import com.aero.scoped.GuiceScoped;
import com.aero.utils.UiActions;
import com.aero.waiters.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsCommon<T extends AbsCommon> {
  protected WebDriver driver;
  protected Waiter waiter;
  protected UiActions uiActions;
  protected GuiceScoped guiceScoped;


  public AbsCommon(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.getDriver();
    this.waiter = new Waiter(driver).getWaitDriver();
    this.uiActions = new UiActions(guiceScoped.getDriver());
    PageFactory.initElements(driver, this);
  }

}
