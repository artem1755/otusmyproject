package com.aero.common;

import com.aero.scoped.GuiceScoped;
import com.aero.utils.UiActions;
import com.aero.waiters.Waiter;
import com.google.inject.Inject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsCommon<T extends AbsCommon> {
  protected WebDriver driver;
  protected Waiter waiter;
  protected UiActions uiActions;
  protected GuiceScoped guiceScoped;

  @Inject
  @SuppressFBWarnings(
          value = "EI_EXPOSE_REP2",
          justification = "GuiceScoped — это DI-контекст, управляемый Cucumber+Guice. "
                  + "Ссылка на объект передаётся через инъекцию и используется только внутри тестов."
  )
  public AbsCommon(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.getDriver();
    this.waiter = new Waiter(driver).getWaitDriver();
    this.uiActions = new UiActions(guiceScoped.getDriver());
    PageFactory.initElements(driver, this);
  }

}
