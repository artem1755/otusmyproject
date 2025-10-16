package com.aero.pages;

import com.aero.components.CourseItem;
import com.aero.models.CourseDTO;
import com.aero.utils.PropertyLoader;
import com.aero.waiters.Waiter;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.aero.utils.StringUtils.extractPath;

@SuppressFBWarnings(
        value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
        justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
)
public class CoursesPage {
  WebDriver driver;
  WebDriverWait wait;
  @FindBy(xpath = "//section[@class='sc-o4bnil-0 riKpM']/div[2]//a")
  List<WebElement> coursesItems;
  By activeCategories = By.xpath("//span[contains(text(),'Свернуть')]/..//div[@value='true']//label");

  public CoursesPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
  }

  public CoursesPage open() {
    driver.get(PropertyLoader.getBaseUrl() + "/catalog/courses");
    return this;
  }

  public CourseItem getCourseItemsByTitle(String title) {
    return coursesItems.stream()
            .map(elem -> new CourseItem(elem, driver))
            .filter(item -> item.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Курс с названием " + title + " не найден"));
  }

  public List<String> getActiveCategories() {
    if (!new Waiter(driver).waitForElementVisible(activeCategories)) {
      throw new RuntimeException("Не дождались активных категорий");
    }

    return driver.findElements(activeCategories).stream()
            .map(el -> el.getText().trim())
            .toList();
  }

  public List<CourseItem> getAllCourses() {
    Waiter waiter = new Waiter(driver);
    waiter.waitForElementVisible(By.xpath("//section[@class='sc-o4bnil-0 riKpM']/div[2]//a"));
    return coursesItems.stream()
            .map(elem -> new CourseItem(elem, driver))
            .collect(Collectors.toList());
  }

  public List<CourseDTO> getAllEarliestCourses() {
    List<CourseItem> allCourses = getAllCourses();

    CourseItem minCourse = allCourses.stream()
            .filter(c -> !c.getStartDateString().equals("О дате старта будет объявлено позже"))
            .reduce((c1, c2) -> c1.getStartDate().isBefore(c2.getStartDate()) ? c1 : c2)
            .orElseThrow();

    LocalDate minDate = minCourse.getStartDate();
    return allCourses.stream()
            .filter(c -> !c.getStartDateString().equals("О дате старта будет объявлено позже"))
            .filter(c -> c.getStartDate().equals(minDate))
            .map(elem -> new CourseDTO(
                            elem.getTitle(),
                            extractPath(elem.getHref()),
                            elem.getStartDate()
                    )
            )
            .collect(Collectors.toList());
  }

  public List<CourseDTO> getAllLatestCourses() {
    List<CourseItem> allCourses = getAllCourses();

    CourseItem maxCourse = allCourses.stream()
            .filter(c -> !c.getStartDateString().equals("О дате старта будет объявлено позже"))
            .reduce((c1, c2) -> c1.getStartDate().isAfter(c2.getStartDate()) ? c1 : c2)
            .orElseThrow();

    LocalDate maxDate = maxCourse.getStartDate();
    return allCourses.stream()
            .filter(c -> !c.getStartDateString().equals("О дате старта будет объявлено позже"))
            .filter(c -> c.getStartDate().equals(maxDate))
            .map(elem -> new CourseDTO(
                            elem.getTitle(),
                            extractPath(elem.getHref()),
                            elem.getStartDate()
                    )
            )
            .collect(Collectors.toList());
  }

  public void checkThatTheDesiredCategoryIsOpened(String categoryName) {
    List<String> titles = getActiveCategories();
    Assertions.assertEquals(1, titles.size());
    Assertions.assertEquals(categoryName, titles.getFirst());
  }
}
