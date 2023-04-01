package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            article_title = "//*[@content-desc='%s']",
            footer_element = "//*[@content-desc='View article in browser']",
            save_article_button = "//android.widget.TextView[@content-desc='Save']",
            close_article_button = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getArticleTitleLocator(String substring) {
        return String.format(article_title, substring);
    }

    public String getArticleTitle(String title) {
        WebElement element = waitForTitleElement(title);
        return element.getAttribute("content-desc");
    }

    public WebElement waitForTitleElement(String title) {
        return this.waitForElementPresent(By.xpath(getArticleTitleLocator(title)),
                "Cannot find article title on page", 10);
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(footer_element),
                "Cannot find the end of article", 20);
    }

    public void addArticleToMyList() {
        this.waitForElementAndClick(
                By.xpath(save_article_button),
                "Cannot find options button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(close_article_button),
                "Cannot find navigate button",
                5
        );
    }

    public void assertTitleElementWithoutWait(String title) {
        this.assertElementPresent(
                By.xpath(getArticleTitleLocator(title)),
                "Title of article not exist"
        );
    }
}
