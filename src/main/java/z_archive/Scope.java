package z_archive;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Scope {

    public static void main(String[] args) throws InterruptedException {

        // Инициализация WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Неявное ожидание
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Ожидание появления первой колонки ссылок
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement columnDriver = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr/td[1]/ul")));

        // Получаем ссылки один раз, чтобы избежать StaleElementReferenceException
        List<WebElement> links = columnDriver.findElements(By.tagName("a"));
        int numberOfLinks = links.size();
        System.out.println("Total links in first column: " + numberOfLinks);

        // Открываем все найденные ссылки в новых вкладках
        for (int i = 0; i < numberOfLinks; i++) {
            int attempts = 0; // Количество попыток клика
            boolean success = false; // Флаг успешного клика

            while (attempts < 3 && !success) {
                try {
                    // Перезапрашиваем ссылку заново перед кликом
                    WebElement link = columnDriver.findElements(By.tagName("a")).get(i);

                    // Ожидаем кликабельность
                    wait.until(ExpectedConditions.elementToBeClickable(link));

                    // Открываем ссылку в новой вкладке (CTRL + ENTER через JavaScript)
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.open(arguments[0].href, '_blank');", link);

                    System.out.println("Opened link in new tab: " + link.getText());

                    success = true; // Если успешно кликнули, выходим из цикла
                } catch (StaleElementReferenceException e) {
                    attempts++;
                    System.out.println("Caught StaleElementReferenceException, retry " + attempts + "...");
                    Thread.sleep(500); // Даем немного времени DOM
                } catch (Exception e) {
                    System.out.println("Error clicking link: " + e.getMessage());
                    break;
                }
            }
        }

        // Не закрываем браузер сразу, чтобы увидеть открытые вкладки
    }
}
