package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorsTestNG {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginFailure() {
        driver.get("https://sso.teachable.com/secure/9521/identity/login/password?force=true");

        // Ввод неверных данных
        driver.findElement(By.id("email")).sendKeys("yuliya.kalcheuskaya@gmail.com");
        driver.findElement(By.name("password")).sendKeys("hello123");
        driver.findElement(By.className("btn-primary")).click();
        driver.findElement(By.xpath("a[href='/sign_out']")).click();

        // Проверяем, что после неудачного входа появилась ошибка
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        String errorMessage = errorElement.getText();

        Assert.assertEquals(errorMessage, "Invalid email or password.", "Ошибка входа не соответствует ожидаемой.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
