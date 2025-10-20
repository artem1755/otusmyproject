package com.aero.steps.common;

import com.aero.pages.MainPage;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;

public class CommonSteps {
  @Inject
  MainPage mainPage;

  @Пусть("Я открываю браузер {string}")
  public void openPage(String browserName) {
    mainPage.open();
  }
}
