package com.aero.steps.blocks;

import com.aero.context.TestContext;
import com.aero.pages.CourseDetailPage;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;

public class CourseDetailPageSteps {

  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  private CourseDetailPage courseDetailPage;

  @Inject
  private TestContext testContext;

  @Тогда("детальная страница должна быть равной рандомной категории")
  public void hoverOnTheRandomCourseAndClick() {
    courseDetailPage.checkThatPageIsCorrect(testContext.getSelectedCourse());
  }

  @Тогда("название курса на детальной странице должно быть равным {string}")
  public void courseTitleShouldBeEqualsTo(String title) {
    courseDetailPage.checkThatPageIsCorrect(title);
  }
}
