package com.aero.pages;

import com.aero.models.CourseDTO;
import com.aero.utils.PropertyLoader;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.aero.utils.StringUtils.formatToDayMonth;

public class CourseDetailPage {

  private final WebDriver driver;
  private final WebDriverWait wait;

  @FindBy(xpath = "//h1")
  private WebElement element;

  public CourseDetailPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
    PageFactory.initElements(driver, this);
  }

  public void checkThatPageIsCorrect(String title) {
    WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
    Assertions.assertEquals(title, visibleElement.getText());
  }


  /**
   * Проверяет через Jsoup, что на странице курса правильные данные
   */
  @SuppressFBWarnings(
          value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
          justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
  )
  public void verifyCourseDataWithJsoup(List<CourseDTO> courses) {
    for (CourseDTO course : courses) {
      try {
        Document doc = Jsoup.connect(
                PropertyLoader.getBaseUrl() + course.getHref()
        ).get();

        Element h1 = doc.selectFirst("h1");
        Element date = doc.selectFirst("p.sc-1x9oq14-0.sc-3cb1l3-0.doSDez.dgWykw");

        // Проверяем, что h1 найден
        Assertions.assertNotNull(
                h1,
                "На странице курса не найден тег <h1> (" + course.getHref() + ")"
        );

        // Проверяем, что дата найдена
        Assertions.assertNotNull(
                date,
                "На странице курса не найден элемент с датой (" + course.getHref() + ")"
        );

        String h1Text = h1.text();
        String dateText = date.text();
        String formattedDate = formatToDayMonth(course.getStartDate());

        // Проверка названия курса
        Assertions.assertEquals(
                course.getTitle(),
                h1Text,
                "Название курса не совпадает на странице: " + course.getHref()
        );

        // Проверка даты
        Assertions.assertTrue(
                dateText.contains(formattedDate),
                String.format(
                        "Дата начала курса не совпадает. Ожидали: '%s', на странице: '%s' (%s)",
                        formattedDate, dateText, course.getHref()
                )
        );

      } catch (Exception e) {
        throw new RuntimeException(
                "Ошибка при проверке страницы курса: " + course.getHref(),
                e
        );
      }
    }
  }
}
