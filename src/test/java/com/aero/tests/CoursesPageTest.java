package com.aero.tests;

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

  @Test
  void checkThatCourseIsAvailable() {
    String courseTitle = "Fullstack developer";

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
