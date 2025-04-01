package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Base {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Открываем сайт
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");

        // Добавим небольшую задержку, чтобы страница точно загрузилась
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Список нужных овощей
        List<String> veggiesNeeded = Arrays.asList("Cucumber", "Brocolli", "Beetroot");

        // Находим все продукты
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

        // Добавляем в корзину все, что нужно
        products.stream()
                .filter(product -> isProductNeeded(product.getText(), veggiesNeeded))
                .forEach(product -> addToCart(product, driver));

        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();


        // Закрываем браузер после выполнения
//        driver.quit();
    }

    private static boolean isProductNeeded(String productName, List<String> veggiesNeeded) {
        return veggiesNeeded.stream().anyMatch(productName::contains);
    }

    private static void addToCart(WebElement product, WebDriver driver) {
        // Находим нужную кнопку относительно продукта и кликаем
        WebElement addButton = product.findElement(By.xpath("following-sibling::div/button[text()='ADD TO CART']"));
        addButton.click();
    }

}
