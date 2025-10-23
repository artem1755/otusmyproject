package com.aero.tests;

import com.aero.jupiter.anno.HtmlFromJsoup;
import com.aero.jupiter.extensions.UiExtension;
import com.aero.models.CourseDTO;
import com.aero.pages.CourseDetailPage;
import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(UiExtension.class)
public class CoursesPageTest {

  @Inject
  CoursesPage coursesPage;

  @Inject
  MainPage mainPage;

  @Inject
  CourseDetailPage detailPage;

  /**
   * Аннотация HtmlFromJsoup используется для получения тестовых данных перед тестом.
   * По url и cssQuery получаем значение dom-элемента и пробрасываем это значение в тест при помощи механизма Junit ext.
   * Это сделалось для того, чтобы тест не падал часто (т.к. меняются курсы на странице каталога курсов и зашитое в тест
   * значение названия курса становится не актуальным).
   */
  @Test
  @HtmlFromJsoup(
          url = "/catalog/courses/",
          cssQuery = "h6.sc-1x9oq14-0.sc-1yg5ro0-1.enpOeQ.frUeNO.sc-hrqzy3-0.cYNMRM.sc-1yg5ro0-0.iPwMHk > div.sc-hrqzy3-1.jEGzDf"
  )
  void checkThatCourseIsAvailable(String course) {
    String courseTitle = course;

    coursesPage.openPage()
            .getCourseItemsByTitle(courseTitle)
            .gotoDetailPage()
            .checkThatPageIsCorrect(courseTitle);
  }

  @Test
  void checkEarliestAndLatestCourses() {
    coursesPage.openPage();
    List<CourseDTO> earliestCourses = coursesPage.getAllEarliestCourses();
    List<CourseDTO> latestCourses = coursesPage.getAllLatestCourses();

    List<CourseDTO> allCourses = Stream
            .concat(earliestCourses.stream(), latestCourses.stream())
            .collect(Collectors.toList());

    detailPage.verifyCourseDataWithJsoup(allCourses);
  }

  @Test
  void checkThatTheRandomCategoryWillOpenCorrectly() {
    String expectedCategory = mainPage.openPage()
            .header()
            .selectRandomCategory();

    coursesPage.checkThatTheDesiredCategoryIsOpened(expectedCategory);
  }

}
