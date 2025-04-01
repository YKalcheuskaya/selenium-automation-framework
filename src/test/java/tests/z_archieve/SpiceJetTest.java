package tests.z_archieve;

import org.openqa.selenium.WebDriver;
import z_archive.SpiceJetHomePage;

public class SpiceJetTest {

    public static void main(String[] args) {
        // Инициализация драйвера с опциями
        WebDriver driver = SpiceJetHomePage.initializeDriver();

        // Создаем объект страницы
        SpiceJetHomePage homePage = new SpiceJetHomePage(driver);

        // Открываем страницу
        homePage.openSpiceJetPage();

        // Выбираем город отправления
        homePage.selectFromCity("Bhuj (BHJ)");

        // Можно дописать методы для выбора To, даты и пассажиров
    }
}
