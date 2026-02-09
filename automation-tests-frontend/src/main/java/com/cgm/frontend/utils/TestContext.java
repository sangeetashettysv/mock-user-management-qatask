package com.cgm.frontend.utils;

import org.openqa.selenium.WebDriver;
import com.cgm.frontend.pages.UsersListPage;
import com.cgm.frontend.pages.UpdatePage;

public class TestContext {

    private WebDriver driver;

    // Page Objects
    private UsersListPage usersListPage;
    private UpdatePage updatePage;

    // ---------- Driver ----------
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // ---------- UsersListPage ----------
    public UsersListPage getUsersListPage() {
        if (usersListPage == null && driver != null) {
            usersListPage = new UsersListPage(driver);
        }
        return usersListPage;
    }

    public void setUsersListPage(UsersListPage usersListPage) {
        this.usersListPage = usersListPage;
    }

    // ---------- UpdatePage ----------
    public UpdatePage getUpdatePage() {
        if (updatePage == null && driver != null) {
            updatePage = new UpdatePage(driver);
        }
        return updatePage;
    }

    public void setUpdatePage(UpdatePage updatePage) {
        this.updatePage = updatePage;
    }

    // ---------- Reset ----------
    public void reset() {
        usersListPage = null;
        updatePage = null;
    }
}