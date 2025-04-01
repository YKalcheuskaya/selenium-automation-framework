package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightBooking {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");  // убираем всплывашки

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.spicejet.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // === Выбор "From" ===
        WebElement fromDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), 'From')]")
        ));
        fromDropdown.click();

        // Вводим "Bhu"
        WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'From')]/following-sibling::div//input[@type='text']")
        ));
        fromInput.sendKeys("Bhu");

        // Ждем и выбираем Bhuj
        WebElement fromCity = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Bhuj']")
        ));
        fromCity.click();
        System.out.println("From is: Bhuj");

        // === Выбор "To" ===
        WebElement toDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), 'To')]")
        ));
        toDropdown.click();

        // Вводим "Mum"
        WebElement toInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'To')]/following-sibling::div//input[@type='text']")
        ));
        toInput.sendKeys("Mum");

        // Ждем и выбираем Mumbai
        WebElement toCity = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Mumbai']")
        ));
        toCity.click();
        System.out.println("To is: Mumbai");

        driver.quit();
    }
}
