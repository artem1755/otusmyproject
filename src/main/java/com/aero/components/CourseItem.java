package com.aero.components;

import com.aero.pages.CourseDetailPage;
import com.aero.waiters.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourseItem {
    private final WebDriver driver;
    private final WebElement root;
    Waiter waiter;

    public CourseItem(WebElement root, WebDriver driver) {
        this.driver = driver;
        this.root = root;
        this.waiter = new Waiter(driver);
    }

    private final By titleLocator = By.cssSelector("h6 > div");

    public String getTitle() {
        return root.findElement(titleLocator).getText();
    }

    public CourseDetailPage gotoDetailPage(){
        if (waiter.waitForElementClickable(root.findElement(titleLocator))) {
            root.findElement(titleLocator).click();
//            UiActions.click(driver, root.findElement(titleLocator));
        } else {
            throw new RuntimeException("Элемент с заголовком курса не кликабелен");
        }

        return new CourseDetailPage(driver);
    }
}
