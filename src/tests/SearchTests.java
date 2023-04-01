package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCheckTextInSearchField(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
    }

    @Test
    public void testSearchResult(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){

        SearchPageObject searchPageObject = new SearchPageObject(this.driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Google");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String search_text = "Linking Lark discography";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results>0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String search_text = "Awasdasdesd";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }
}
