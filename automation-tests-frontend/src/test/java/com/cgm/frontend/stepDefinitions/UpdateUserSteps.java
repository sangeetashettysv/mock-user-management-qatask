package com.cgm.frontend.stepDefinitions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.cgm.frontend.pages.UpdatePage;
import com.cgm.frontend.pages.UsersListPage;
import com.cgm.frontend.utils.TestContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateUserSteps {

    private TestContext context;
    private UsersListPage usersListPage;
    private UpdatePage updatePage;

    private String updatedPhone;

    public UpdateUserSteps(TestContext context) {
        this.context = context;
    }

    // -------- Common modal interactions --------

    @When("user clicks the Update action for a specific user")
    public void user_clicks_the_update_action_for_a_specific_user() {
        WebDriver driver = context.getDriver();

        if (context.getUsersListPage() == null) {
            context.setUsersListPage(new UsersListPage(driver));
        }
        usersListPage = context.getUsersListPage();

        if (context.getUpdatePage() == null) {
            context.setUpdatePage(new UpdatePage(driver));
        }
        updatePage = context.getUpdatePage();

        usersListPage.clickUpdateForFirstUser();
    }

    @Then("Update User modal should be displayed")
    public void update_user_modal_should_be_displayed() {
        assertTrue(updatePage.isUpdateModalDisplayed(), "Update modal is not displayed");
    }

    @Then("all fields except Phone should be disabled")
    public void all_fields_except_phone_should_be_disabled() {
        assertTrue(updatePage.areNonEditableFieldsDisabled(), "Some fields are editable when they should not be");
        assertTrue(updatePage.isPhoneFieldEnabled(), "Phone field is not enabled");
    }

    // -------- Positive update --------

    @When("user edits the Phone field with valid data")
    public void user_edits_the_phone_field_with_valid_data() {
        updatedPhone = "1122334455";
        updatePage.updatePhone(updatedPhone);
    }

    @When("user clicks Update Phone")
    public void user_clicks_update_phone() {
        updatePage.clickUpdatePhone();
    }

    @Then("updated phone should be visible in the Users List")
    public void updated_phone_should_be_visible_in_the_users_list() {
        
        usersListPage.waitForPhoneToUpdate(updatedPhone);
        // Get the phone number from the users list for the first user
        String currentPhone = usersListPage.getFirstUserPhone();

        // Verify it matches the updated value
        assertEquals(currentPhone, updatedPhone, 
            "Phone value in Users List does not match updated value");
    }

    @Then("modal should close")
    public void modal_should_close() {
        assertTrue(updatePage.isModalClosed(), "Modal did not close");
    }

    // -------- Intentional failure scenario --------

    @When("user enters invalid phone data")
    public void user_enters_invalid_phone_data() {
        updatePage.updatePhone("INVALID_PHONE");
        updatePage.clickUpdatePhone();
    }

    @Then("modal should close for invalid input")
    public void modal_should_close_for_invalid_input() {
        assertFalse(updatePage.isModalClosed(), "Intentional failure: Modal closed even with invalid phone");
    }

    // -------- error validation --------

    @When("user enters phone number with less than 10 digits")
    public void user_enters_phone_number_with_less_than_10_digits() {
        updatePage.updatePhone("12345");
    }

    @Then("error message below Phone field should be displayed")
    public void error_message_below_phone_field_should_be_displayed() {
        String expectedMessage = "Phone must be a 10-digit number (eg. 9812345678)";
        String actualMessage = updatePage.getPhoneValidationErrorText();
        assertEquals(actualMessage, expectedMessage,
                "Validation message is incorrect for short phone number");
}

    @Then("Update Phone button should be disabled")
    public void update_phone_button_should_be_disabled() {
        assertFalse(updatePage.isUpdatePhoneButtonEnabled(),
                "Update Phone button is enabled despite validation error");
    }

}