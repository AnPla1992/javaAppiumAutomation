import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Android_8");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
        capabilities.setCapability("app","D:\\practics\\javaAppiumAutomation\\apks\\wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/fragment_onboarding_skip_button')]"),
                "Cann't find button 'SKIP'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/view_announcement_action_negative')]"),
                "Cann't find button 'Got It'",
                5
        );
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testCheckTextInSearchField(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cann't find field for input search text",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "The field does not contain the expected text"
        );
    }

    @Test
    public void testCancelSearch(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cann't find field for input search text",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Google",
                "Cann't find field for input search text",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Multilingual neural machine translation service')]"),
                "Cann't find item list of results",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cann't fiend cancel search button",
                5
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Multilingual neural machine translation service')]"),
                "Element close search is visible on the page",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "The search field isn't clean"
        );
    }


    /*============================================================================================*/

    private WebElement assertElementHasText(By by, String text, String errorMessage){
        WebElement element = waitForElementPresent(by, "Cannot find element", 5);
        Assert.assertEquals(
                errorMessage,
                text,
                element.getText());
        return element;
    }


    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
