package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SpiceJetHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SpiceJetHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для инициализации драйвера с ChromeOptions (рекомендуется)
    public static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");  // Отключаем всплывающие уведомления
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        return new ChromeDriver(options);
    }

    // Метод для открытия страницы SpiceJet
    public void openSpiceJetPage() {
        driver.get("https://www.spicejet.com/");
    }

    // Пример метода для выбора направления "From"
    public void selectFromCity(String cityName) {
        WebElement fromDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("r-1862ga2")));
        fromDropdown.click();

        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + cityName + "']")));
        cityOption.click();
    }

    // Тут можно добавить еще методы для выбора "To", даты, пассажиров и т.д.
}
