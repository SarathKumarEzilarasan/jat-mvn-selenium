package tests;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.ButtonPage;
import pages.TextBoxPage;
import utilities.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    TextBoxPage textBoxPage;
    ButtonPage buttonPage;
    String url;
    String browser;
    ClickUtils clickUtils;
    WaitUtils waitUtils;
    ElementUtils elementUtils;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        QaEnvProps.init();
        TestDataReader.init();
        TestDataJsonReader.init();
        url = QaEnvProps.getProperty("url");
//        browser = QaEnvProps.getProperty("browser");
        this.browser = browser;
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else {
            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        textBoxPage = new TextBoxPage(driver);
        buttonPage = new ButtonPage(driver);
        clickUtils = new ClickUtils(driver);
        waitUtils = new WaitUtils(driver);
        elementUtils = new ElementUtils(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static String takeScreenShot(String testCaseName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File destinationFile = new File("screenshots/" + testCaseName + ".png");
        try {
            FileUtils.copyFile(scrFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String path = "<img src= " + destinationFile.getAbsolutePath() + " width = 200px height=200px />";

        Reporter.log(path);
        return destinationFile.getAbsolutePath();
    }
}
