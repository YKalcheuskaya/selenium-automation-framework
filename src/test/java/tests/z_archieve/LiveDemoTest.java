package tests.z_archieve;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import z_archive.LiveDemo;

import java.util.List;

public class LiveDemoTest {
    WebDriver driver;
    LiveDemo liveDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        liveDemo = new LiveDemo(driver);
    }

    @Test
    public void testLiveDemoSorting() {
        // Получаем список перед сортировкой
        List<String> beforeSort = liveDemo.getOriginalList();
        System.out.println("Before Sorting: " + beforeSort);

        // Нажимаем на заголовок для сортировки
        liveDemo.clickOnColumn();

        // Получаем новый список после сортировки
        List<String> afterSort = liveDemo.getOriginalList();
        System.out.println("After Sorting (UI): " + afterSort);

        // Проверяем, что список действительно отсортирован
        List<String> sortedList = afterSort.stream().sorted().toList();
        System.out.println("Expected Sorted List: " + sortedList);

        Assert.assertEquals(afterSort, sortedList, "Sorting failed!");
    }

    @Test
    public void testFindBeansPrice() {
        String vegetable = "Beans";

        // Используем поле поиска вместо перебора страниц
        liveDemo.searchVegetable(vegetable);

        // Получаем цену
        String price = liveDemo.getPriceOfVegetable(vegetable);

        System.out.println("Price of " + vegetable + ": " + price);

        // Проверяем, что нашли овощ
        Assert.assertNotEquals(price, "Vegetable not found", "The vegetable '" + vegetable + "' was not found in the table!");
    }

    @Test
    public void testFindVegetablePrice() {
        String vegetable = "Pineapple";

        // Используем поиск
        liveDemo.searchVegetable(vegetable);

        // Получаем цену
        String price = liveDemo.getPriceOfVegetable(vegetable);

        System.out.println("Price of " + vegetable + ": " + price);

        // Проверяем, что нашли овощ
        Assert.assertNotEquals(price, "Vegetable not found", "The vegetable '" + vegetable + "' was not found in the table!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
