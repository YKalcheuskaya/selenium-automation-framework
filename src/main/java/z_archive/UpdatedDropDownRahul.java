package z_archive;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpdatedDropDownRahul {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем чекбокс Senior Citizen
        WebElement seniorCitizenCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")
        ));

        // Печатаем статус до клика
        System.out.println("Before click - Selected: " + seniorCitizenCheckbox.isSelected());

        // Кликаем
        seniorCitizenCheckbox.click();

        // Печатаем статус после клика
        System.out.println("After click - Selected: " + seniorCitizenCheckbox.isSelected());

        driver.quit();
    }
}

