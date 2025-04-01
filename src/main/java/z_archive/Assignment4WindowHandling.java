package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Assignment4WindowHandling {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");

        driver.findElement(By.xpath("(//a[normalize-space()='Click Here'])[1]")).click();

        String parentId = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String windowHandle : windows) {
            if (!windowHandle.equals(parentId)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println(driver.findElement(By.tagName("h3")).getText());

        driver.switchTo().window(parentId);

        driver.quit();
    }
}
