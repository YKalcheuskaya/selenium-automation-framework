package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Assignment6DataTableExtractor {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();


        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Find a table
        WebElement table = driver.findElement(By.cssSelector("table.table-display"));

        // Find all rows in the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        System.out.println("Total amount of the rows is " + rowCount);

        // Find all columns in the table
        List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));
        int columnCount = columns.size();
        System.out.println("Total amount of column is " + columnCount);

        if (rowCount > 1) {
            List<WebElement> secondRowFind = rows.get(1).findElements(By.tagName("td"));
            System.out.println("Second row represent: " + secondRowFind);
        } else {
            System.out.println("Table doesn't have enough rows");
        }
    }
}
