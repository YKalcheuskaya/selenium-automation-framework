package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FrameTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");

        // Переключаемся на iframe (он единственный на странице)
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        // Теперь Selenium видит элемент draggable
        driver.findElement(By.id("draggable")).click();

        Actions a = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        a.dragAndDrop(source, target).build().perform();

        // Возвращаемся в основной контент страницы
        driver.switchTo().defaultContent();



        // Закрываем браузер
        driver.quit();
    }
}
