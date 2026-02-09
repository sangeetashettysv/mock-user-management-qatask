package com.cgm.frontend.stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.cgm.frontend.pages.UsersListPage;
import com.cgm.frontend.utils.DriverManager;
import com.cgm.frontend.utils.TestContext;

import io.cucumber.java.en.Given;

public class CommonSteps {

    private TestContext context;
    private UsersListPage usersListPage;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("user opens the application on {string}")
    public void user_opens_the_application_on(String browser) {
        // Initialize WebDriver only if not already done
        WebDriver driver = context.getDriver();
        if (driver == null) {
            driver = DriverManager.getDriver(browser);
            context.setDriver(driver);
        }

        // Initialize UsersListPage only if not already done
        if (context.getUsersListPage() == null) {
            context.setUsersListPage(new UsersListPage(driver));
        }
        this.usersListPage = context.getUsersListPage();

        // Open the application
        usersListPage.openApplication();
    }
}