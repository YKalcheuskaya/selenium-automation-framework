package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Список карточек товаров
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    // Локатор, чтобы дождаться появления карточек
    By productsBy = By.cssSelector(".mb-3");

    // Анимационный спиннер (появляется после кликов)
    @FindBy(css = ".ng-animating")
    WebElement animation;

    // Кнопка перехода в корзину (верхнее меню)
    @FindBy(css = "[routerlink='/dashboard/cart']")
    WebElement cartHeader;

    // Ждём и возвращаем список товаров
    public List<WebElement> getProductList() {
        waitForElementToAppear(products.get(0));
        return products;
    }

    // Получить конкретный товар по имени
    public WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
    }

    // Добавить товар в корзину и дождаться завершения анимации
    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]")).click();

        // ⏳ Дожидаемся исчезновения спиннера (обязательный шаг!)
        waitForElementToDisappear(animation);
    }

    // Переход в корзину — ждём пока спиннер исчезнет, потом кликаем
    public CartPage goToCartPage() {
        waitForElementToDisappear(animation);
        cartHeader.click();
        return new CartPage(driver);
    }
}
