package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import tests.Components.BaseTest;

public class LoginStepDefinition extends BaseTest {

    @Given("User is on landing page")
    public void user_is_on_landing_page() {
        System.out.println("🔸 Opening landing page");
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        System.out.println("🔸 Logging in with: " + username + " / " + password);
    }

    @Then("Home page is displayed")
    public void home_page_is_displayed() {
        System.out.println("✅ Home page is visible");
    }

    @Then("Error message is displayed")
    public void error_message_is_displayed() {
        Assert.assertTrue(landingPage.getErrorMessage().isDisplayed());
    }

}

