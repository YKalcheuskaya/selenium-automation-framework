package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class JavaScriptExecutorDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        int sum = 0;
        for (int i = 0; i < values.size(); i++) {
            String valueStr = values.get(i).getText().trim();
            try {
                int valueInt = Integer.parseInt(valueStr);
                sum += valueInt;
                System.out.println("Converted value: " + valueInt);
            } catch (NumberFormatException e) {
                System.out.println("Error converting: " + valueStr);
            }
        }
        System.out.println("Total value: " + sum);

        driver.findElement(By.cssSelector(".totalAmount")).getText();

        driver.quit();
    }
}
