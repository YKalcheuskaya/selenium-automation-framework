package tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import tests.Components.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void placeOrderTest(HashMap<String, String> input) throws IOException {
        // Login
        ProductCatalogue productCatalogue = landingPage.login(
                input.get("email"),
                input.get("password")
        );
        test.log(Status.INFO, "Logged in with email: " + input.get("email"));

        // Add Product to Cart
        productCatalogue.addProductToCart(input.get("product"));
        test.log(Status.INFO, "Added product to cart: " + input.get("product"));

        // Go to Cart Page
        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.isProductInCart(input.get("product")), "Product is not in the cart");
        test.log(Status.PASS, "✅ Product added to cart successfully!");

        // Checkout
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Belarus");
        test.log(Status.INFO, "Selected country: Belarus");

        // Submit Order
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."), "Confirmation message mismatch");
        test.log(Status.PASS, "✅ Order placed successfully!");

        // Screenshot
        String screenshotPath = takeScreenshot("placeOrderTest", driver);
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Test
    public void testIsRunning() {
        System.out.println("🔥 Dummy test is running!");
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty(
                "user.dir") + "/src/test/java/data/PurchaseOrder.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }


}
