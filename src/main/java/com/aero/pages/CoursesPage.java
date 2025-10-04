package com.aero.pages;

import com.aero.components.CourseItem;
import com.aero.utils.PropertyLoader;
import com.aero.waiters.Waiter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CoursesPage {
    WebDriver driver;
    WebDriverWait wait;

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyLoader.getBaseTimeout()));
    }

    public CoursesPage open(){
        driver.get(PropertyLoader.getBaseUrl()+"/catalog/courses");
        return this;
    }

    @FindBy(xpath = "//section[@class='sc-o4bnil-0 riKpM']/div[2]//a")
    List<WebElement> coursesItems;

    By activeCategories = By.xpath("//span[contains(text(),'Свернуть')]/..//div[@value='true']//label");

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

    public void checkThatTheDesiredCategoryIsOpened(String categoryName){
        List<String> titles = getActiveCategories();
        Assertions.assertEquals(1,titles.size());
        Assertions.assertEquals(categoryName,titles.getFirst());
    }

}
