package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_DESCRIPTION_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
            SEARCH_DESCRIPTION_CHOICE = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']",
            SEARCH_TITLE_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            CLEAR_LINE = "org.wikipedia:id/search_close_btn",
            SEARCH_LIST_RESULT = "//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_title')]",
            SEARCH_END_OF_PAGE = "//*[@text='View page in browser']",
            DOUBLE_SEARCH="//android.widget.TextView[@text='{TITLE})']//../android.widget.TextView[@text='{DESCRIPTION}']",
            SEARCH_ARTICLE_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_container']";


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
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
        waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after click", 5);
        waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click", 5);
    }

    public void initSearchChoice() {
        waitForElementPresent(By.xpath(SEARCH_DESCRIPTION_CHOICE), "Cannot find search input after click", 5);
        waitForElementAndClick(By.xpath(SEARCH_DESCRIPTION_CHOICE), "Cannot find and click", 5);
    }

    public void inputInSearchLine(String inputLine) {
        waitForElementAndSendKeys(By.id(SEARCH_INPUT), inputLine, "Cannot find input line", 5);
    }

    public void waitForSearchResultByXpath(String substring) {
        String newSearchResult = getResultSearchDescriptionElement(substring);
        waitForElementPresent(By.xpath(newSearchResult), "Cannot find search result", 15);
    }

    public void waitForSearchResultByTitle(String substring) {
        String newSearchResult = getResultSearchTitleElement(substring);
        waitForElementAndClick(By.xpath(newSearchResult), "Cannot find search result", 15);
    }

    public void waitForSearchResultTitle() {
        waitForElementAndClick(By.xpath(SEARCH_DESCRIPTION_RESULT), "Cannot find search result", 15);
    }

    public boolean waitForSearchResultByTitleIsDisplayed(String substring) {
        String newSearchResult = getResultSearchTitleElement(substring);
        return waitForElementPresent(By.xpath(newSearchResult), "Cannot find search result", 15).isDisplayed();
    }

    public void clearSearchLine() {
        waitForElementAndClear(By.id(CLEAR_LINE), "Cannot find clear line", 10);
    }

    public boolean searchLineIsDisplayed() {
        return waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after click", 5).isDisplayed();
    }

    public List<WebElement> getListOfResults() {
        return assertElementsHasText(By.xpath(SEARCH_LIST_RESULT), "message", 10);
    }

    public void swipe(int maxSwipe) {
        swipeToElement(By.xpath(SEARCH_END_OF_PAGE),
                "Cannot go to the end of page",
                maxSwipe);
    }

    public void  waitForElementByTitleAndDescription(String title, String description){
        getResultSearchTitleAndDescriptionElement(title, description);
    }

    public void clickOnDoubleSearch(){
        waitForElementPresent(By.xpath(DOUBLE_SEARCH), "Cannot find article with title and description", 15);
    }

    public List<WebElement> getListOfArticleResults() {
        return assertElementsHasText(By.xpath(SEARCH_ARTICLE_LIST), "Cannot find list of articles", 10);
    }
}
