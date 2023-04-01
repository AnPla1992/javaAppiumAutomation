package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        String article = "Object-oriented programming language";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        articlePageObject.addArticleToMyList();
        articlePageObject.closeArticle();
        searchPageObject.clearSearchField();
        searchPageObject.goBackToMainPage();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickSaved();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openSavedArticleList();
        myListPageObject.deleteArticleFromList(article);
        myListPageObject.waitForArticleToDisappearByTitle(article);
    }

    @Test
    public void testSaveArticlesToMyList() {

        SearchPageObject searchPageObject = new SearchPageObject(this.driver);
        String article_first = "Object-oriented programming language",
                article_second = "Automation for Apps";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(article_first);
        ArticlePageObject articlePageObject = new ArticlePageObject(this.driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        articlePageObject.addArticleToMyList();
        articlePageObject.closeArticle();
        searchPageObject.clearSearchField();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring(article_second);
        articlePageObject.waitForTitleElement(article_second);
        articlePageObject.addArticleToMyList();
        articlePageObject.closeArticle();
        searchPageObject.goBackToMainPage();
        NavigationUI navigationUI = new NavigationUI(this.driver);
        navigationUI.clickSaved();
        MyListPageObject myListPageObject = new MyListPageObject(this.driver);
        myListPageObject.openSavedArticleList();
        myListPageObject.deleteArticleFromList(article_first);
        myListPageObject.waitForArticleToDisappearByTitle(article_first);
        myListPageObject.waitForArticleToAppearByTitle(article_second);
        myListPageObject.openArticleFromList(article_second);
        String actual_title = articlePageObject.getArticleTitle(article_second);

        Assert.assertEquals(
                "Title of article not equal expected value 'Automation for Apps'",
                "Automation for Apps",
                actual_title
        );
    }

}
