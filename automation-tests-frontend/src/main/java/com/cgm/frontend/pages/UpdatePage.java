package com.cgm.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

public class UpdatePage extends BasePage {

    public UpdatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ===== Locators =====

    private By updateModal = By.cssSelector("div[id='updateUserModal'] div[class='modal-dialog']");

    private By modalTitle = By.cssSelector("div[id='updateUserModal'] h5[class='modal-title']");

    private By nameField = By.cssSelector("input[name='name'][disabled]");
    private By usernameField = By.cssSelector("input[name='username'][disabled]");
    private By emailField = By.cssSelector("input[name='email'][disabled]");
    private By websiteField = By.cssSelector("input[name='website'][disabled]");

    private By phoneField = By.xpath("/html/body/div[1]/div/main/div/div[4]/div/div/div[2]/input[4]");
    private By phoneError = By.cssSelector("div.invalid-feedback");

    private By updatePhoneButton = By.cssSelector("button[type='button'][class='btn btn-primary']");
    private By closeButton = By.cssSelector("div[id='updateUserModal'] div[class='modal-footer'] button:nth-child(1)");
    private By closeIcon = By.cssSelector("div[id='updateUserModal'] button[aria-label='Close']");

    // ===== Actions =====

    public boolean isUpdateModalDisplayed() {
        waitForVisibility(updateModal);
        return driver.findElement(updateModal).isDisplayed();
    }

    public boolean areNonEditableFieldsDisabled() {
        return !driver.findElement(nameField).isEnabled()
                && !driver.findElement(usernameField).isEnabled()
                && !driver.findElement(emailField).isEnabled()
                && !driver.findElement(websiteField).isEnabled();
    }

    public boolean isPhoneFieldEnabled() {
        return driver.findElement(phoneField).isEnabled();
    }

    public void updatePhone(String newPhone) {
        waitForVisibility(phoneField);
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(newPhone);
    }

    public String getPhoneValue() {
        return driver.findElement(phoneField).getAttribute("value");
    }

    public void clickUpdatePhone() {
        waitForVisibility(updatePhoneButton);
        driver.findElement(updatePhoneButton).click();
    }

    public boolean isModalClosed() {
        try {
            return !driver.findElement(updateModal).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    // ===== New validation scenario methods =====

    public boolean isPhoneValidationErrorDisplayed() {
        try {
            waitForVisibility(phoneError);
            return driver.findElement(phoneError).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUpdatePhoneButtonEnabled() {
        return driver.findElement(updatePhoneButton).isEnabled();
    }

    public String getPhoneValidationErrorText() {
        try {
            waitForVisibility(phoneError);
            return driver.findElement(phoneError).getText().trim();
        } catch (NoSuchElementException e) {
            return ""; // Return empty string if error not displayed
        }
    }
}
