package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String
            MORE_OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc=\"More options\"]",

    TITLE_OF_SOME_ELEMENT = "//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Java (programming language)']",
            SOME_MY_LIST = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{articleName}']";

    private String getResultOfSomeList(String substring) {
        return SOME_MY_LIST.replace("{articleName}", substring);
    }

    public void clickMoreOptionsButton() {
        waitForElementAndClick(By.xpath(MORE_OPTIONS_BUTTON), "Cannot find toolbar_button", 20);
    }

    public String getSomeTitle() {
        return assertElementPresent(By.xpath(TITLE_OF_SOME_ELEMENT), "title");
    }

    public void clickSomeListButton(String nameOfList) {
        String newString = getResultOfSomeList(nameOfList);
        waitForElementAndClick(By.xpath(newString), "Cannot find onboarding_button", 15);
    }
}
