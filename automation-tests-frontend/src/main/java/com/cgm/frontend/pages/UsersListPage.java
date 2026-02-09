package com.cgm.frontend.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cgm.frontend.utils.ConfigReader;

public class UsersListPage extends BasePage {

    public UsersListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Update button for first user
    private By firstUserUpdateButton =
            By.cssSelector("a[data-bs-toggle='modal'][data-bs-target='#updateUserModal']");

    private By firstUserPhone = By.xpath("/html/body/div/div/main/div/div[1]/div[2]/table/tbody/tr[1]/td[4]"); 

    // Open application
    public void openApplication() {
        driver.get(ConfigReader.getEnvironmentUrl());
    }

    // Click Update for first user
    public void clickUpdateForFirstUser() {
        waitForVisibility(firstUserUpdateButton);
        driver.findElement(firstUserUpdateButton).click();
    }

    public String getFirstUserPhone() {
        waitForVisibility(firstUserPhone);
        return driver.findElement(firstUserPhone).getText().trim();
    }

    public void waitForPhoneToUpdate(String expectedPhone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> getFirstUserPhone().equals(expectedPhone));
    }
}