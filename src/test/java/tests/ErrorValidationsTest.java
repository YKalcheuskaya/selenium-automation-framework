package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import tests.Components.BaseTest;

import java.io.IOException;


public class ErrorValidationsTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        String email = "wrong@email.com";
        String password = "wrongPass";
        //login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(email, password);
        Assert.fail("Login should have failed with incorrect credentials");
    }
}
