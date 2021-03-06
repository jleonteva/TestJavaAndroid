import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTest");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/Desktop/JavaAppiumAuto/apks/Wikipedia_v2.7.50362-r-2021-06-04_apkpure.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }
    @After
    public void tearDown()
    {
        driver.quit();

    }
    @Test
    public void firstTest()
    {
        WebElement skipButton = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        skipButton.click();
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        element_to_init_search.click();

        WebElement element_to_enter_line = waitForElementPresentByXpath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Cannot find search input",
                5
        );
        element_to_enter_line.click();

        WebElement search_text_input = waitForElementPresentById(
                "org.wikipedia:id/search_src_text",
                "Cannot find search text input",
                10);
        search_text_input.sendKeys("Java");


                //driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        //element_to_enter_line.sendKeys("Java");
        //waitForElementPresentByXpath(
        //        "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']",
        //        "???? ?????????? ???????????? Java",
        //        15
       // );


    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }
}
