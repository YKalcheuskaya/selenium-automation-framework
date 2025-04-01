package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pageobjects.ProductCatalogue; // Импорт, если нужен
import tests.Components.BaseTest;

public class LoginStepDefinition extends BaseTest {

    @Given("User is on landing page")
    public void user_is_on_landing_page() {
        landingPage.goTo();  // Добавить вызов goTo()
        System.out.println("🔸 Opening landing page");
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        landingPage.login(username, password); // Вызываем login()
        System.out.println("🔸 Logging in with: " + username + " / " + password);
    }

    // Test Comment for Git

    @Then("Home page is displayed")
    public void home_page_is_displayed() {
        System.out.println("✅ Home page is visible");
        // Здесь в будущем можно будет добавить assert на ProductCatalogue
    }

    @Then("Error message is displayed")
    public void error_message_is_displayed() {
        Assert.assertTrue(landingPage.getErrorMessage().isDisplayed());
        System.out.println("❌ Error message is shown");
    }
}

