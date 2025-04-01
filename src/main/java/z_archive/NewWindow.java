package z_archive;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class NewWindow {

    public static void main(String[] args) throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindowId = it.next();
        driver.switchTo().window(childWindowId);
        driver.get("https://rahulshettyacademy.com/");

        String courseName = driver.findElement(By.xpath(
                "//a[contains(text(),'Core java for Automation Testers + Interview Progr')]")).getText();
        driver.switchTo().window(parentWindowId);
        WebElement name = driver.findElement(By.cssSelector("[name='name']"));
        name.sendKeys(courseName);
        File file = name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("logo.png"));
        System.out.println(name.getRect().getDimension().getHeight());
        System.out.println(name.getRect().getDimension().getWidth());

        driver.quit();
    }
}
