package com.aero.steps.common;

import com.aero.scoped.GuiceScoped;
import com.aero.utils.PropertyLoader;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;

public class CommonSteps {
  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Я открываю браузер {string}")
  public void openPage(String browserName) {
    guiceScoped.getDriver().get(PropertyLoader.getBaseUrl());
  }
}
