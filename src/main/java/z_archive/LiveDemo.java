package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class LiveDemo {
    WebDriver driver;

    public LiveDemo(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnColumn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/thead/tr/th[1]"))).click();
    }

    public List<String> getOriginalList() {
        List<WebElement> elementsList = driver.findElements(By.xpath("//tr//td[1]"));
        return elementsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void searchVegetable(String vegetableName) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchBox.clear();
        searchBox.sendKeys(vegetableName);

        // Ожидание появления элемента в таблице
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//tbody/tr/td[1]"), vegetableName));
    }

    public String getPriceOfVegetable(String vegetableName) {
        try {
            WebElement element = driver.findElement(By.xpath("//tbody/tr/td[1][text()='" + vegetableName + "']"));
            WebElement priceElement = element.findElement(By.xpath("./following-sibling::td[1]"));
            return priceElement.getText();
        } catch (Exception e) {
            return "Vegetable not found";
        }
    }
}
