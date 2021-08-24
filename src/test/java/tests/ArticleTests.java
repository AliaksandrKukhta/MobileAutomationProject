package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.mobile_web.MWArticlePageObject;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    private static final String
        LOGIN="AliaksandrKukhta",
        PASSWORD="vfhrtnbyu2021";
    @Test
    public void testOfSaveTwoArticles() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        MWArticlePageObject mwArticlePageObject = new MWArticlePageObject(driver);
        String firstSubject = "Java";
        String secondSubject = "Appium";
        String articleName = "Java Article";
        String substring = "bject-oriented programming language";

        if (Platform.getInstance().isAndroid()){
            searchPageObject.initSearchInput();
            searchPageObject.inputInSearchLine(firstSubject);
            Thread.sleep(5000);
            searchPageObject.initSearchChoice();
            articlePageObject.clickMoreOptionsButton();
            myListPageObject.clickAddToReadingListButton();
            myListPageObject.clickOnboardingButton();
            myListPageObject.inputInListLineNaming(articleName);
            myListPageObject.clickOKButton();
            myListPageObject.clickExitButtonFromMyLists();
            searchPageObject.initSearchInput();
            searchPageObject.inputInSearchLine(secondSubject);
            searchPageObject.waitForSearchResultByTitle(secondSubject);
            articlePageObject.clickMoreOptionsButton();
            myListPageObject.clickAddToReadingListButton();
            articlePageObject.clickSomeListButton(articleName);
            myListPageObject.clickExitButtonFromMyLists();
            navigationUI.clickMyListButton();
            navigationUI.openList();
            navigationUI.lestSwipe();
            assertTrue(searchPageObject.waitForSearchResultByTitleIsDisplayed(secondSubject));
        } else if (Platform.getInstance().isMW()){
            int allArticals=0;
            int articalsAfterDelete=0;
            mwArticlePageObject.initSearchInput();
            mwArticlePageObject.inputInSearchLine(firstSubject);
            Thread.sleep(5000);
            mwArticlePageObject.initSearchChoice();
            mwArticlePageObject.addToWatchlist();
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.EnterLoginAndPassword(LOGIN, PASSWORD);
            authorizationPageObject.submitForm();
            Thread.sleep(5000);
            searchPageObject.initSearchInput();
            searchPageObject.inputInSearchLine(secondSubject);
            searchPageObject.waitForSearchResultByTitle(secondSubject);
            mwArticlePageObject.addToWatchlist();
            mwArticlePageObject.clickMenuButton();
            mwArticlePageObject.openWatchlistInMenu();
            allArticals=mwArticlePageObject.getSavedArticles().size();
            mwArticlePageObject.waitForSearchResultByClass("1");
            articalsAfterDelete=mwArticlePageObject.getSavedArticles().size();
            Assert.assertNotEquals(allArticals, articalsAfterDelete);
        } else {
            System.out.println("Cannot find test for another platform");
        }
    }
}
