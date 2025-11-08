package com.aero.components;

import static com.aero.utils.StringUtils.extractDatePart;
import static com.aero.utils.StringUtils.parseDate;

import com.aero.pages.CourseDetailPage;
import com.aero.waiters.Waiter;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SuppressFBWarnings(
        value = {
          "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
          "EI_EXPOSE_REP2"
        },
        justification = "RuntimeException выбрасывается намеренно, WebElement хранится безопасно для тестов"
)
public class CourseItem {
  private final WebDriver driver;
  private final WebElement root;
  private final By titleLocator = By.cssSelector("h6 > div");
  private final By date = By.xpath("div[2]/div/div");
  private final Waiter waiter;

  public CourseItem(WebElement root, WebDriver driver) {
    this.driver = driver;
    this.root = root;
    this.waiter = new Waiter(driver);
  }

  public String getTitle() {
    return root.findElement(titleLocator).getText();
  }

  public CourseDetailPage gotoDetailPage() {
    if (waiter.waitForElementClickable(root.findElement(titleLocator))) {
      root.findElement(titleLocator).click();
    } else {
      throw new RuntimeException("Элемент с заголовком курса не кликабелен");
    }
    return new CourseDetailPage(driver);
  }

  public String getStartDateString() {
    return root.findElement(date).getText().trim();
  }

  public LocalDate getStartDate() {
    return parseDate(
            extractDatePart(
                    getStartDateString()
            )
    );
  }

  public String getHref() {
    return root.getAttribute("href");
  }

  public String getFormattedStartDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));
    return getStartDate().format(formatter);
  }
}
