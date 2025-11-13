package com.aero.steps.blocks;

import com.aero.components.HeaderComponent;
import com.aero.context.TestContext;
import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  private HeaderComponent headerComponent;

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private TestContext testContext;

  @Пусть("главная странциа открыта")
  public void openMainPage() {
    mainPage.openPage();
  }

  @Пусть("происходит наведение на блок с курсами и клик на рандомный курс")
  public void hoverOnTheRandomCourseAndClick() {
    String course = headerComponent.selectRandomCategory();
    testContext.setSelectedCourse(course);
  }

  @Тогда("проверяем, что открылась верная категория")
  public void goToDetail() {
    coursesPage.checkThatTheDesiredCategoryIsOpened(testContext.getSelectedCourse());
  }

}
