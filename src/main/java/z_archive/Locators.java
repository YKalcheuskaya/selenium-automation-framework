package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Locators {

    public static void main(String[] args) {
//          WebDriver driver = new ChromeDriver();
//          WebDriver driver = new FirefoxDriver();
          WebDriver driver = new EdgeDriver();

        // Устанавливаем неявное ожидание 5 секунд
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Создаем объект WebDriverWait с таймаутом 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Переход на страницу логина
            driver.get("https://sso.teachable.com/secure/9521/identity/login/password?force=true");
            System.out.println("Title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());

            // Ввод неверных данных
            driver.findElement(By.id("email")).sendKeys("yuliya.kalcheuskaya@gmail.com");
            driver.findElement(By.name("password")).sendKeys("hello123");
            driver.findElement(By.className("btn-primary")).click();

            // Ожидание появления и кликабельности ссылки "Forgot Password" и клик по ней
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot Password"))).click();

            // Перезапрашиваем элемент email на странице "Forgot Password" перед каждым действием
            WebElement forgotEmailField = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(By.name("email"))
            ));
            forgotEmailField.sendKeys("yuliya.kalch@gmail.com");

            // Повторное нахождение элемента перед очисткой
            forgotEmailField = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(By.name("email"))
            ));
            forgotEmailField.clear();
            forgotEmailField.sendKeys("yuliya.kalcheuskaya@gmail.com");

            // Нажатие на кнопку "commit"
            wait.until(ExpectedConditions.elementToBeClickable(By.name("commit"))).click();

            // Ожидание появления элемента с классом "dsp-flex-xs"
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dsp-flex-xs")));

            // Нажимаем кнопку, найденную по имени "button"
            wait.until(ExpectedConditions.elementToBeClickable(By.name("button"))).click();

            // Нажимаем ссылку "Return to login"
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Return to login"))).click();

            driver.findElement(By.cssSelector("#email")).sendKeys("yuliya.kalcheuskaya@gmail.com");
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("hello123");
            driver.findElement(By.id("remember_me")).click();
            driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]")).click();
            driver.findElement(By.xpath("a[href='/sign_out']")).click();

        } catch (Exception e) {
            e.printStackTrace();  // Вывод ошибок в консоль
        } finally {
            // Закрытие браузера
            driver.quit();
        }
    }

        public void getPassword(WebDriver driver) {
        driver.get("https://sso.teachable.com/secure/9521/identity/login/password?force=true");
        driver.findElement(By.linkText("Forgot Password")).click();


    }
}
