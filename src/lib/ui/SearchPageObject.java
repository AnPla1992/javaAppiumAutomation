package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
        search_init_element = "//*[contains(@text,'Search Wikipedia')]",
        search_input = "//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]",
        search_cancel_button = "org.wikipedia:id/search_close_btn",
        search_result_title_by_id_tpl = "//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[contains(@text,'%s') and contains(@resource-id,'org.wikipedia:id/page_list_item_description')]",
        clear_query_button = "//android.widget.ImageView[@content-desc='Clear query']",
        go_back_button = "//*[@resource-id='org.wikipedia:id/search_toolbar']/android.widget.ImageButton",
        search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']",
        empty_result_label = "//*[@resource-id='org.wikipedia:id/results_text'][@text='No results']";
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* Templates method */
    private static String getResultSearchElement(String substring) {
        return String.format(search_result_title_by_id_tpl, substring);
    }
    /* Templates method */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(search_init_element),
                "Cannot find field for input search request after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(search_init_element),
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(search_input),
                "Cannot find field for input search request after clicking search init element", 5);
    }

    public void typeSearchLine(String value) {
        this.waitForElementAndSendKey(By.xpath(search_input),
                value,"Cannot find field for input search request and input request", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(search_cancel_button),
                "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(search_cancel_button),
                "Search cancel button are exist on page", 5);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(By.id(search_cancel_button),
                "Cannot find and click search cancel button", 5);
    }

    public void waitForSearchResult(String result) {
        String search_result_title_xpath = getResultSearchElement(result);
        this.waitForElementPresent(By.xpath(search_result_title_xpath),
                "Cannot find search request result", 10);
    }

    public void clickByArticleWithSubstring(String result) {
        String search_result_title_xpath = getResultSearchElement(result);
        this.waitForElementAndClick(By.xpath(search_result_title_xpath),
                "Cannot find and click search request result", 10);
    }

    public void goBackToMainPage() {
        this.waitForElementAndClick(
                By.xpath(clear_query_button),
                "Cannot find options cancel search button",
                10
        );
        this.waitForElementAndClick(
                By.xpath(go_back_button),
                "Cannot find back button",
                5
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath( search_result_locator),
                "Cannot find anything by the request",
                10
        );

        return this.getAmountOfElements(By.xpath(search_result_locator));
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty search result label for request",
                10
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(search_result_locator),"We've found some results by request");
    }
}
