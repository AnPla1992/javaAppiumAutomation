package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
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
        searchPageObject.goBackToMainPage();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickSaved();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openSavedArticleList();
        myListPageObject.deleteArticleFromList(article);
        myListPageObject.waitForArticleToDisappearByTitle(article);
    }

}
