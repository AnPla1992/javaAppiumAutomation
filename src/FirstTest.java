import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;
    private String searchEditLocator = "//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]";

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
        driver.rotate(ScreenOrientation.PORTRAIT);

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/fragment_onboarding_skip_button')]"),
                "Cann't find button 'SKIP'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/view_announcement_action_negative')]"),
                "Cann't find button 'Got It'",
                10
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

    @Test
    public void testValidateSearchResult(){
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
        waitForElementsAndCheckContainText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Google",
                "Not all list items contain Google in the title"
        );

    }

    @Test
    public void testSaveArticlesToMyList() {
        String saveButtonLocator = "//android.widget.TextView[@content-desc='Save']";
        String navigationUpButtonLocator = "//android.widget.ImageButton[@content-desc='Navigate up']";
        String clearQueryButtonLocator = "//android.widget.ImageView[@content-desc='Clear query']";

        //find and save first article
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cann't find field for input search text",
                5
        );
        waitForElementAndSendKey(
                By.xpath(searchEditLocator),
                "Java",
                "Cann't find field for input search text",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[contains(@text,'Object-oriented programming language') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cann't find field for input search text",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@content-desc='Java (programming language)']"),
                "Cannot find article title",
                10
        );

        waitForElementAndClick(
                By.xpath(saveButtonLocator),
                "Cannot find 'Save' button",
                5
        );

        waitForElementAndClick(
                By.xpath(navigationUpButtonLocator),
                "Cannot find navigate button",
                5
        );

        waitForElementAndClick(
                By.xpath(clearQueryButtonLocator),
                "Cannot find options cancel search button",
                10
        );

        //find and save second article
        waitForElementAndSendKey(
                By.xpath(searchEditLocator),
                "Appium",
                "Cann't find field for input search text",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[contains(@text,'Automation for Apps') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cann't find field for input search text",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@content-desc='Automation for Apps']"),
                "Cannot find article title",
                10
        );

        waitForElementAndClick(
                By.xpath(saveButtonLocator),
                "Cannot find 'Save' button",
                5
        );

        waitForElementAndClick(
                By.xpath(navigationUpButtonLocator),
                "Cannot find navigate button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_toolbar']/android.widget.ImageButton"),
                "Cannot find back button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/nav_tab_reading_lists']"),
                "Cannot find button of bottom menu 'Saved'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/recycler_view']"),
                "Cannot find list of saved articles 'Saved'",
                5
        );

        swipeElementToLeft(
                By.xpath("//android.view.ViewGroup[@resource-id='org.wikipedia:id/reading_list_swipe_refresh']//android.widget.TextView[contains(@text,'Object-oriented programming language') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cannot find item list of saved articles"
        );

        waitForElementNotPresent(
                By.xpath("//android.view.ViewGroup[@resource-id='org.wikipedia:id/reading_list_swipe_refresh']//android.widget.TextView[contains(@text,'Object-oriented programming language') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Articles by Java exist in list of saved articles",
                5
        );

        waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@resource-id='org.wikipedia:id/reading_list_swipe_refresh']//android.widget.TextView[contains(@text,'Automation for Apps') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cannot find article about Appium in saved list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@resource-id='org.wikipedia:id/reading_list_swipe_refresh']//android.widget.TextView[contains(@text,'Automation for Apps') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cannot find article in saved list",
                5
        );

        String actual_title = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "content-desc",
                "Cannot find title of article about Appium",
                10
        );

        Assert.assertEquals(
                "Title of article not equal expected value 'Automation for Apps'",
                "Automation for Apps",
                actual_title
        );
    }

    @Test
    public void testInstantAssertTitleOfArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cann't find field for input search text",
                5
        );
        waitForElementAndSendKey(
                By.xpath(this.searchEditLocator),
                "Java",
                "Cann't find field for input search text",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[contains(@text,'Object-oriented programming language') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]"),
                "Cann't find item list of results",
                5
        );

        assertElementPresent(
                By.xpath("//*[@content-desc='Java (programming language)']"),
                "Title of article not exist"
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

    private List<WebElement> waitForElementsAndCheckContainText(By by, String text, String errorMessage){
        List<WebElement> elements = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement element : elements) {
            Assert.assertTrue(
                    errorMessage,
                    element.getText().contains(text));
        }
        return elements;
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

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = element.getSize().getHeight();
        int middle_y = upper_y + lower_y/2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementPresent(By by, String errorMessage){
        waitForElementPresent(by, errorMessage,0);
    }
}
