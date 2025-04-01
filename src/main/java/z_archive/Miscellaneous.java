package z_archive;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Miscellaneous {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().deleteCookieNamed("sessionKey");

        driver.get("https://www.google.com/");

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("/Users/julia/Downloads/screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();


    }
}
