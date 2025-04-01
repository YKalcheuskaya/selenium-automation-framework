package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3NestedFrameTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Переключаемся на верхний фрейм (top frame)
        driver.switchTo().frame("frame-top");

        // Переключаемся в средний фрейм (middle frame)
        driver.switchTo().frame("frame-middle");

        // Находим и печатаем текст
        WebElement middleText = driver.findElement(By.tagName("div"));
        System.out.println(middleText.getText());

        // Возвращаемся в основной документ
        driver.switchTo().defaultContent();

        // Закрываем браузер
        driver.quit();
    }
}
