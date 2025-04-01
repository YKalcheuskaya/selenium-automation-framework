package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarClass {

    public static void main(String[] args) throws InterruptedException {

        String month = "6"; // июнь
        String date = "16";
        String year = "2025";

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.cssSelector(".react-date-picker__inputGroup__year")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label__labelText.react-calendar__navigation__label__labelText--from")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label__labelText.react-calendar__navigation__label__labelText--from")).click();
        driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
        int monthIndex = Integer.parseInt(month) - 1;
        driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(monthIndex).click();
        driver.findElement(By.cssSelector("abbr[aria-label='June 16, 2025']")).click();
        System.out.println(driver.findElement(By.cssSelector(".react-date-picker__inputGroup")).getText());








    }
}