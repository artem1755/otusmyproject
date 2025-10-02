package com.aero.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CourseDetailPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//h1")
    private WebElement element;

    public CourseDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        PageFactory.initElements(driver, this);
    }

    public void checkThatPageIsCorrect(String title) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        Assertions.assertEquals(title, visibleElement.getText());
    }
}
