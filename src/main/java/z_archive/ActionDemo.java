package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        Actions a = new Actions(driver);
        WebElement move = driver.findElement(By.className("nav-line-2"));

        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click()
                .keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
        a.moveToElement(move).contextClick().build().perform();



    }
}
