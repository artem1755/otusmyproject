package com.aero.hooks;

import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.After;

public class Hooks {

  @Inject
  private GuiceScoped guiceScoped;

  @After()
  public void afterScenario() {
    if (guiceScoped.driver != null) {
      guiceScoped.driver.close();
      guiceScoped.driver.quit();
    }
  }
}
