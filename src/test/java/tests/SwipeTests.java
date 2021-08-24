package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SwipeTests extends CoreTestCase {
    @Test
    public void testSwipe() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String inputLine = "Appium";
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine(inputLine);
        Thread.sleep(5000);
        searchPageObject.waitForSearchResultByTitle(inputLine);
        searchPageObject.swipe(10);
    }
}
