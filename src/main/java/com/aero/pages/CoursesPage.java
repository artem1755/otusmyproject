package com.aero.pages;

import static com.aero.utils.StringUtils.extractPath;

import com.aero.annotations.Path;
import com.aero.components.CourseItem;
import com.aero.models.CourseDTO;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressFBWarnings(
        value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
        justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
)
@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {

  @FindBy(xpath = "//section[@class='sc-o4bnil-0 riKpM']/div[2]//a")
  List<WebElement> coursesItems;



  By activeCategories = By.xpath("//span[contains(text(),'Свернуть')]/..//div[@value='true']//label");

  @Inject
  public CoursesPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public CourseItem getCourseItemsByTitle(String title) {
    return coursesItems.stream()
            .map(elem -> new CourseItem(elem, guiceScoped))
            .filter(item -> item.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Курс с названием " + title + " не найден"));
  }

  public List<String> getActiveCategories() {
    if (!waiter.waitForElementVisible(activeCategories)) {
      throw new RuntimeException("Не дождались активных категорий");
    }

    return driver.findElements(activeCategories).stream()
            .map(el -> el.getText().trim())
            .toList();
  }

  public List<CourseItem> getAllCourses() {
    waiter.waitForElementVisible(By.xpath("//section[@class='sc-o4bnil-0 riKpM']/div[2]//a"));
    return coursesItems.stream()
            .map(elem -> new CourseItem(elem, guiceScoped))
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

  public List<CourseDTO> getAllCoursesThatEqualsToDateOrLater(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
    LocalDate formattedDate = LocalDate.parse(date, formatter);

    List<CourseItem> allCourses = getAllCourses();

    return allCourses.stream()
            .filter(c -> !c.getStartDateString().equals("О дате старта будет объявлено позже"))
            .filter(courseItem -> !courseItem.getStartDate().isBefore(formattedDate))
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
    Assertions.assertEquals(categoryName, titles.get(0));
  }

  public Map<String, String> checkTheCheapestAndMostExpensiveCourses() {
    List<String> hrefs = getAllCourses().stream()
            .map(x -> x.getHref())
            .collect(Collectors.toList());


    Map<String, String> courses = new HashMap<>();

    for (String href : hrefs) {
      try {
        Document doc = Jsoup.connect(href).get();

        Element titleElement = doc.selectFirst("main h3");
        Element priceElement = doc.selectFirst(".sc-153sikp-11.gztHyx");

        if ((titleElement == null) || (priceElement == null)) {
          continue;
        }
        String courseTitle = titleElement != null ? titleElement.text() : "Название не найдено";
        String coursePrice = priceElement != null ? priceElement.text() : "Цена не найдена";

        courses.put(courseTitle, coursePrice);

      } catch (IOException e) {
        System.err.println("Ошибка при загрузке страницы: " + href);
        e.printStackTrace();
      }
    }

    return courses;
  }
}
