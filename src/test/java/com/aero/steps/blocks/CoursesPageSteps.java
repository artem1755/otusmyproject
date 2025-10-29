package com.aero.steps.blocks;

import com.aero.context.TestContext;
import com.aero.models.CourseDTO;
import com.aero.pages.CoursesPage;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

import java.util.List;


public class CoursesPageSteps {
  @Inject
  private CoursesPage coursesPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  private TestContext testContext;

  @Пусть("каталог курсов открыт")
  public void openCoursePage() {
    coursesPage.openPage();
  }

  @Тогда("найти курсы, которые стартуют {string} или позже")
  public void findCoursesStartingOnOrAfter(String date) {
    List<CourseDTO> allCoursesThatEqualsToDateOrLater = coursesPage.getAllCoursesThatEqualsToDateOrLater(date);

    allCoursesThatEqualsToDateOrLater.stream()
            .forEach(course -> {
              System.out.printf("Курс: %s | Дата старта: %s%n",
                      course.getTitle(),
                      course.getStartDate());
            });
  }

  @Тогда("найти курс с названием {string} и открыть его на детальной странице")
  public void findCourseByTitle(String title) {
    coursesPage.getCourseItemsByTitle(title).gotoDetailPage();
  }
}
