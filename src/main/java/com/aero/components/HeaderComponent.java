package com.aero.components;

import com.aero.utils.StringUtils;
import com.aero.utils.UiActions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Random;

@SuppressFBWarnings(
        value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
        justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
)
public class HeaderComponent extends AbsBaseComponent{
  private static final Random RANDOM = new Random();

  private final By trainingMenu = By.xpath("//span[@title='Обучение']/..");
  private final By categoryItems = By.xpath("//p[contains(text(),'Все курсы')]/../div/a");

  public HeaderComponent(WebDriver driver) {
    super(driver);
  }

  public String selectRandomCategory() {
    if (!waiter.waitForElementVisible(trainingMenu)) {
      throw new RuntimeException("Меню 'Обучение' не найдено");
    }

    WebElement training = driver.findElement(trainingMenu);
    UiActions.hover(driver, training);

    if (!waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(categoryItems))) {
      throw new RuntimeException("Категории в меню 'Обучение' не появились");
    }

    List<WebElement> categories = driver.findElements(categoryItems);
    WebElement randomCategory = categories.get(RANDOM.nextInt(categories.size()));
    String selectedCategory = randomCategory.getText().trim();

    if (!waiter.waitForElementClickable(randomCategory)) {
      throw new RuntimeException("Категория '" + selectedCategory + "' не кликабельна");
    }

    UiActions.click(driver, randomCategory);

    return StringUtils.removeBrackets(selectedCategory);
  }

}
