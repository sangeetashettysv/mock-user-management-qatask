package com.cgm.frontend.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    // Wait until element is visible
    protected void waitForVisibility(By locator){
        try {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
        System.out.println("Element not found: " + locator.toString());
        throw new RuntimeException("Element not found after waiting: " + locator.toString());
        }
    }
}
