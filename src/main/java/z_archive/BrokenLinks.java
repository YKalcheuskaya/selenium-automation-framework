package z_archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");


            // Получаем все ссылки в списке
            List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));

            for (WebElement link : links) {
                String url = link.getAttribute("href");

                // Проверяем, что URL не null
                if (url == null || url.isEmpty()) {
                    System.out.println("⚠️ Пропущена пустая ссылка");
                    continue;
                }

                // Открываем соединение
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");

                // Получаем статус-код
                int responseCode = connection.getResponseCode();

                // Проверяем статус ссылки
                if (responseCode >= 400) {
                    System.out.println("❌ Битая ссылка: " + url + " (Код ошибки: " + responseCode + ")");
                } else {
                    System.out.println("✅ Рабочая ссылка: " + url + " (Код: " + responseCode + ")");
                }

                // Закрываем соединение
                connection.disconnect();
            }

        } catch (IOException e) {
            System.out.println("Ошибка при проверке ссылки: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
