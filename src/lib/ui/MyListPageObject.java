package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends  MainPageObject{

    private static final String
        saved_list = "//*[@resource-id='org.wikipedia:id/recycler_view']",
        item_saved_list_by_id = "//android.view.ViewGroup[@resource-id='org.wikipedia:id/reading_list_swipe_refresh']//android.widget.TextView[contains(@text,'%s') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]";
    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return String.format(item_saved_list_by_id, substring);
    }

    public void openSavedArticleList(){
        this.waitForElementAndClick(
                By.xpath(saved_list),
                "Cannot find saved article in list",
                5
        );
    }

    public void deleteArticleFromList(String article) {
        this.swipeElementToLeft(
                By.xpath(getResultSearchElement(article)),
                "Cannot find article '" + article + "' in list of saved articles"
        );
    }

    public void waitForArticleToDisappearByTitle(String article) {
        this.waitForElementNotPresent(
                By.xpath(getResultSearchElement(article)),
                "Article '" + article + "' exist in list of saved articles",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article) {
        this.waitForElementPresent(
                By.xpath(getResultSearchElement(article)),
                "Article '" + article + "' exist in list of saved articles",
                5
        );
    }
}
