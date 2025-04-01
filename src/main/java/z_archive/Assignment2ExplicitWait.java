package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Assignment2ExplicitWait {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        // Открываем сайт
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        // Явное ожидание для надежности (лучше, чем implicitWait)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Вводим логин
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");

        // 2. Вводим пароль
        driver.findElement(By.id("password")).sendKeys("learning");

        // 3. Выбираем радиокнопку "User"
        driver.findElement(By.cssSelector("input[value='user']")).click();

        // Нужно дождаться появления алерта после выбора роли
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();

        // 4. Выбираем "Consultant" из выпадающего списка
        WebElement dropdown = driver.findElement(By.cssSelector("select.form-control"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Consultant");

        // 5. Кликаем чекбокс
        driver.findElement(By.id("terms")).click();

        // 6. Кликаем на кнопку Sign In
        driver.findElement(By.id("signInBtn")).click();

        // Дожидаемся перехода на страницу с товарами
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4.card-title")));

        // 7. Добавляем нужные товары в корзину
        List<String> listOfProducts = Arrays.asList("iphone X", "Samsung Note 8", "Nokia Edge", "Blackberry");
        List<WebElement> products = driver.findElements(By.cssSelector("h4.card-title"));

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getText().trim();
            if (listOfProducts.contains(productName)) {
                // Кликаем на кнопку "Add"
                driver.findElements(By.cssSelector(".card-footer button")).get(i).click();
            }
        }

        // 8. Переходим в корзину
        driver.findElement(By.cssSelector("a.nav-link.btn.btn-primary")).click();

        // driver.quit();
    }
}
