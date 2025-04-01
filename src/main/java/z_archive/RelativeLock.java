package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLock {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // Поиск текстового поля "Name"
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());

        // Поиск поля "Date of Birth"
        WebElement dateOfBirthLabel = driver.findElement(By.cssSelector("label[for='dateofBirth']"));
        WebElement dateOfBirthInput = driver.findElement(with(By.tagName("input")).below(dateOfBirthLabel));

        // Получение атрибута value вместо getText()
        System.out.println(dateOfBirthInput.getAttribute("value"));

        WebElement iceCreamLabel = driver.findElement(By.xpath("//label[normalize-space()='Check me out if you Love IceCreams!']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();

        WebElement radioButton = driver.findElement(By.xpath("//input[@id='inlineRadio1']"));
        System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(radioButton)).getText());

     //   driver.quit(); // Закрытие браузера
    }
}
