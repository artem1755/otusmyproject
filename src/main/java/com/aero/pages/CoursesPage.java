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

    public CourseItem getCourseItemByTitle(String title) {
       return coursesItems.stream()
                .map(elem -> new CourseItem(elem, driver))
                .filter(item -> item.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Курс с названием " + title + " не найден"));
    }

    public List<String> getActiveCategories(){
      List<WebElement> activeCategories =  driver.findElements(By.xpath("//span[contains(text(),'Свернуть')]/../div//label[@value='true']"));
        List<String> labelsText = new ArrayList<>();
        for (WebElement el : activeCategories) {
            labelsText.add(el.getText().trim());
        }
        return labelsText;
    }

    public void checkThatTheDesiredCategoryIsOpened(String categoryName){
        List<String> title = getActiveCategories();
        Assertions.assertEquals(1,getActiveCategories().size());
        Assertions.assertEquals(categoryName,getActiveCategories().getFirst());
    }

}
