package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(css = ".ta-results button")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement placeOrderButton;

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(countryInput, countryName).build().perform();
        waitForElementToAppear(selectCountry);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder() {
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }

}
