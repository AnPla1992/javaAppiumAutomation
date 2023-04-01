package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement assertElementHasText(By by, String text, String errorMessage){
        WebElement element = waitForElementPresent(by, "Cannot find element", 5);
        Assert.assertEquals(
                errorMessage,
                text,
                element.getText());
        return element;
    }

    public List<WebElement> waitForElementsAndCheckContainText(By by, String text, String errorMessage){
        List<WebElement> elements = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement element : elements) {
            Assert.assertTrue(
                    errorMessage,
                    element.getText().contains(text));
        }
        return elements;
    }

    public WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String errorMessage, int max_swipes) {
        for (int i=0;i<max_swipes;i++){
            if(driver.findElements(by).size() != 0)
                return;
            swipeUpQuick();
        }
        waitForElementPresent(by,"Cannot find element by swiping up.\n " + errorMessage, 0);
    }

    public void swipeElementToLeft(By by, String errorMessage) {
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

    public int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String errorMessage) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String defualt_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(defualt_message + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public void assertElementPresent(By by, String errorMessage){
        List<WebElement> elements =  driver.findElements(by);
        Assert.assertTrue(
                errorMessage,
                elements.size() > 0);
    }
}
