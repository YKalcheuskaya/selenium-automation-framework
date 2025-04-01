package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class Assignment7DropDownCheck {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("autocomplete")).sendKeys("united");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("li.ui-menu-item")));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase("United States")) {
                System.out.println("Chosen country: " + option.getText());

                Actions actions = new Actions(driver);
                actions.moveToElement(option).click().perform();
                break;
            }
        }
        driver.quit();
    }
}

