package com.aero.steps.blocks;

import static com.aero.utils.StringUtils.extractPrice;

import com.aero.components.HeaderComponent;
import com.aero.context.TestContext;
import com.aero.models.CourseDTO;
import com.aero.pages.CoursesPage;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoursesPageSteps {
  @Inject
  private CoursesPage coursesPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  private HeaderComponent headerComponent;

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

  @Тогда("найти самые ранние и самые поздние курсы по дате старта")
  public void finAllErliestAndLatestCourses() {
    List<CourseDTO> earliestCourses = coursesPage.getAllEarliestCourses();
    List<CourseDTO> latestCourses = coursesPage.getAllLatestCourses();

    List<CourseDTO> allCourses = Stream
            .concat(earliestCourses.stream(), latestCourses.stream())
            .collect(Collectors.toList());
    testContext.setCourses(allCourses);
  }

  @Пусть("при клике на 'Подготовительные курсы'")
  public void selectOnboardingCourses() {
    headerComponent.clickOnThePreparatoryCoursesItem();
  }

  @Тогда("найти самые дешевые и самые дорогие курсы")
  public void findTheCheapestAndMostExpensiveCourses(){
    Map<String,String> allCourses = coursesPage.checkTheCheapestAndMostExpensiveCourses();

    Map.Entry<String, String> cheapestCourse = allCourses.entrySet().stream()
            .filter(entry -> extractPrice(entry.getValue()) > 0)
            .min(Comparator.comparing(entry -> extractPrice(entry.getValue())))
            .orElseThrow(() -> new IllegalArgumentException("Список пуст"));

    Map.Entry<String, String> expensiveCourse = allCourses.entrySet().stream()
            .filter(entry -> extractPrice(entry.getValue()) > 0)
            .max(Comparator.comparing(entry -> extractPrice(entry.getValue())))
            .orElseThrow(() -> new IllegalArgumentException("Список пуст"));

    System.out.println("Самый дешевый курс это: " + cheapestCourse.getKey()+ " и он стоит "+cheapestCourse.getValue());
    System.out.println("Самый дорогой курс это: " + expensiveCourse.getKey()+ " и он стоит "+expensiveCourse.getValue());
  }
}
