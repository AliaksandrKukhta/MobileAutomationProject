package lib.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MWArticlePageObject extends ArticlePageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "searchIcon",
            SEARCH_INPUT = "search",
            SEARCH_DESCRIPTION_RESULT = ".//*[contains(@title, '{SUBSTRING}')]",
            SEARCH_DESCRIPTION_CHOICE = ".//*[@data-title=\"Java (programming language)\"]",
            SEARCH_TITLE_RESULT = ".//*[contains(@href, '{SUBSTRING}']",
            ADD_TO_WATCHLIST = ".//*[contains(@href, 'watch')]",
            REMOVE_FROM_WATCHLIST = ".//*[contains(@href, 'unwatch')]",
            MENU_BUTTON = "mw-mf-main-menu-button",
            OPEN_WATCHLIST_IN_MENU = ".//*[@data-event-name='menu.unStar']",
            LIST_IN_WATCHLIST = ".//*[@class=\"page-summary with-watchstar\"]",
            SOME_IN_WATCHLIST = ".//*[@class='page-summary with-watchstar'][{SUBSTRING}]";


    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMenuButton() {
        waitForElementPresent(By.id(MENU_BUTTON), "Cannot find menu button", 5);
        waitForElementAndClick(By.id(MENU_BUTTON), "Cannot click menu button", 5);
    }

    public void openWatchlistInMenu() {
        waitForElementPresent(By.xpath(OPEN_WATCHLIST_IN_MENU), "Cannot find menu button", 5);
        waitForElementAndClick(By.xpath(OPEN_WATCHLIST_IN_MENU), "Cannot click menu button", 5);
    }

    public void addToWatchlist() {
        waitForElementAndClick(By.xpath(ADD_TO_WATCHLIST), "Cannot click addToWatchlist button", 15);
    }

    public void waitForSearchResultByClass(String substring) {
        String newSearchResult = getResultSearchElement(substring);
        waitForElementAndClick(By.xpath(newSearchResult), "Cannot find search result", 15);
    }

    private String getResultSearchElement(String substring) {
        return SOME_IN_WATCHLIST.replace("{SUBSTRING}", substring);
    }

    public List<WebElement> getSavedArticles() {
        return assertElementsHasText(By.xpath(LIST_IN_WATCHLIST), "message", 10);
    }

    public void removeFromWatchlist() {
        waitForElementAndClick(By.xpath(REMOVE_FROM_WATCHLIST), "Cannot click removeFromWatchlist button", 15);
    }

    private String getResultSearchDescriptionElement(String substring) {
        return SEARCH_DESCRIPTION_RESULT.replace("{SUBSTRING}", substring);
    }

    private String getResultSearchTitleAndDescriptionElement(String title, String description) {
        String afterFirstChanging = SEARCH_DESCRIPTION_RESULT.replace("{TITLE}", title);
        return afterFirstChanging.replace("{DESCRIPTION}", description);
    }

    private String getResultSearchTitleElement(String substring) {
        return SEARCH_TITLE_RESULT.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find search input after click", 5);
        waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click", 5);
    }

    public void initSearchChoice() {
        waitForElementPresent(By.xpath(SEARCH_DESCRIPTION_CHOICE), "Cannot find search input after click", 5);
        waitForElementAndClick(By.xpath(SEARCH_DESCRIPTION_CHOICE), "Cannot find and click", 5);
    }

    public void inputInSearchLine(String inputLine) {
        waitForElementAndSendKeys(By.name(SEARCH_INPUT), inputLine, "Cannot find input line", 5);
    }
}
