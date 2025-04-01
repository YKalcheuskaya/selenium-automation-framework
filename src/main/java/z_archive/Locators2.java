package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Locators2 {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/practice-project");

        // Явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // XPath для поиска "Blog" после "Practice"
        String xpath = "//li[a[text()='Practice']]/following-sibling::li/a[text()='Blog']";

        // Ожидание элемента
        WebElement blogLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        // Вывод текста найденного элемента
        System.out.println(blogLink.getText());

        // Найдем li через child → parent
        WebElement liElement = driver.findElement(By.xpath("//a[text()='Practice']/parent::li"));

        // Теперь от li идем выше к nav
        WebElement navElement = driver.findElement(By.xpath("//a[text()='Practice']/ancestor::nav"));

        System.out.println("Parent LI: " + liElement.getText());
        System.out.println("Ancestor NAV found!");


        // Закрытие браузера
        driver.quit();
    }
}
