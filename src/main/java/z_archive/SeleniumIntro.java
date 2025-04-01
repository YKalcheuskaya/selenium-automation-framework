package z_archive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class SeleniumIntro {

    public static void main(String[] args) {
        // Use Selenium’s built-in driver management (No WebDriverManager needed)
        EdgeOptions optionsEdge = new EdgeOptions();
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        ChromeOptions optionsChrome = new ChromeOptions();

        WebDriver driverEdge = new EdgeDriver(optionsEdge);
        WebDriver driverFirefox = new FirefoxDriver(optionsFirefox);
        WebDriver driverChrome = new ChromeDriver(optionsChrome);
        List<WebDriver> drivers = List.of(driverChrome, driverFirefox, driverEdge);

        for (WebDriver driver : drivers) {
            try {
                driver.get("https://rahulshettyacademy.com/");

                System.out.println("Title: " + driver.getTitle());
                System.out.println("Current URL: " + driver.getCurrentUrl());
            } finally {
                driver.quit();
            }
        }
    }
}