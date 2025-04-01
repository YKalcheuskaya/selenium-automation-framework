package z_archive;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SSLCheck {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        options.setCapability("proxy", proxy);
        options.setAcceptInsecureCerts(true);
//        FirefoxOptions options1 = new FirefoxOptions();
//        EdgeOptions options3 = new EdgeOptions();
//        options3.setAcceptInsecureCerts(true);
//        options1.setAcceptInsecureCerts(true);


        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        System.out.println(driver.getTitle());

        driver.quit();

    }
}
