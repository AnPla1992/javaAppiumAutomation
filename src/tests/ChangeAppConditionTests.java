package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String search_text = "Java";
        String article_subtitle = "Object-oriented programming language";
        String article_title = "Java (programming language)";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        searchPageObject.clickByArticleWithSubstring(article_subtitle);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement(article_title);
        rotateScreenLandscape();
        articlePageObject.waitForTitleElement(article_title);
        rotateScreenPortrait();
        articlePageObject.waitForTitleElement(article_title);
    }

    @Test
    public void testCheckSearchArticleInBackground(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String search_text = "Java";
        String article_subtitle = "Object-oriented programming language";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        searchPageObject.waitForSearchResult(article_subtitle);
        backgroundApp(10);
        searchPageObject.waitForSearchResult(article_subtitle);
    }
}
