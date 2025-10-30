package com.aero.components;

import com.aero.scoped.GuiceScoped;
import com.aero.utils.StringUtils;
import com.google.inject.Inject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

@SuppressFBWarnings(
        value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
        justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
)
public class HeaderComponent extends AbsBaseComponent{
  private static final Random RANDOM = new Random();

  @FindBy(xpath = "//a[text()='Подготовительные курсы']")
  private WebElement preparingCourses;

  private final By trainingMenu = By.xpath("//span[@title='Обучение']/..");
  private final By categoryItems = By.xpath("//p[contains(text(),'Все курсы')]/../div/a");

  @Inject
  public HeaderComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public String selectRandomCategory() {
    if (!waiter.waitForElementVisible(trainingMenu)) {
      throw new RuntimeException("Меню 'Обучение' не найдено");
    }

    WebElement training = driver.findElement(trainingMenu);
    uiActions.hoverOnElement(training);

    if (!waiter.waitForElementVisible(categoryItems)) {
      throw new RuntimeException("Категории в меню 'Обучение' не появились после hover");
    }

    List<WebElement> categories = driver.findElements(categoryItems);

    Integer random = RANDOM.nextInt(categories.size() - 2);
    WebElement randomCategory = categories.get(random);
    String selectedCategory = randomCategory.getText().trim();

    if (!waiter.waitForElementClickable(randomCategory)) {
      throw new RuntimeException("Категория '" + selectedCategory + "' не кликабельна");
    }

    uiActions.clickOnElement(randomCategory);

    return StringUtils.removeBrackets(selectedCategory);
  }

  public void clickOnThePreparatoryCoursesItem() {
    if (!waiter.waitForElementVisible(trainingMenu)) {
      throw new RuntimeException("Меню 'Обучение' не найдено");
    }

    WebElement training = driver.findElement(trainingMenu);
    uiActions.hoverOnElement(training);
    uiActions.clickOnElement(preparingCourses);
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
