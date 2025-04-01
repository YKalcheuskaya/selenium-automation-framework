package tests.Components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.LandingPage;
import resources.ExtentReporterNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public static LandingPage landingPage;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReporterNG.getReportObject();
    }

    @Parameters("browser")
    public WebDriver initializeDriver(@Optional("chrome") String browserName) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/GlobalData.properties");
        prop.load(fis);

        // если не передан через testng.xml или System.getProperty, берём из properties
        if (browserName == null || browserName.isEmpty()) {
            browserName = System.getProperty("browser") != null
                    ? System.getProperty("browser")
                    : prop.getProperty("browser");
        }

        System.out.println("\uD83C\uDF10 Browser from TestNG/System/properties: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            driver = new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920,1080");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    @Parameters("browser")
    public void launchApplication(@Optional("chrome") String browser, Method method) throws IOException {
        test = extent.createTest(method.getName());
        driver = initializeDriver(browser);
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        test.log(Status.INFO, "Launched application in browser");
    }

    public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
        String jsonContent = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "/src/test/java/data/" + path),
                StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            System.out.println("❌ Test Failed: " + result.getThrowable().getMessage());
            String screenshotPath = takeScreenshot(result.getName(), driver);
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed");
            System.out.println("✅ Test Passed");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReports() {
        extent.flush();
    }

    public String takeScreenshot(String testCaseName, WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String folderPath = System.getProperty("user.dir") + "/reports/screenshots";
        String destPath = folderPath + "/" + testCaseName + "_" + timestamp + ".png";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            FileUtils.copyFile(src, new File(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destPath;
    }
}
