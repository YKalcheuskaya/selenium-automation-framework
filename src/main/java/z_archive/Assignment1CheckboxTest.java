package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Assignment1CheckboxTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement firstCheckBox = driver.findElement(By.id("checkBoxOption1"));

        firstCheckBox.click();
        if(firstCheckBox.isSelected()) {
            System.out.println("First check box is selected");
        } else {
            System.out.println("First check box is not selected");
        }

        firstCheckBox.click();
        if(!firstCheckBox.isSelected()) {
            System.out.println("First check box is  not selected");
        } else {
            System.out.println("First check box is selected");
        }

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("Total checkboxes found: " + checkboxes.size());




    }
}