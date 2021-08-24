package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObject extends MainPageObject {
    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String
            ADD_TO_READING_BUTTON = "//*[@text='Add to reading list']",
            ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
            NAME_OF_INPUT_LIST = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            EXIT_BUTTON_FROM_MY_LISTS = "//*[@class='android.widget.ImageButton']";

    public void clickAddToReadingListButton() {
        waitForElementAndClick(By.xpath(ADD_TO_READING_BUTTON), "Cannot find 'Add to reading list'", 20);
    }

    public void clickOnboardingButton() {
        waitForElementAndClick(By.id(ONBOARDING_BUTTON), "Cannot find onboarding_button", 20);
    }

    public void inputInListLineNaming(String inputLine) {
        waitForElementAndClearAndSendKeys(By.id(NAME_OF_INPUT_LIST), inputLine, 15);
    }

    public void clickOKButton() {
        waitForElementAndClick(By.id(OK_BUTTON), "Cannot find onboarding_button", 15);
    }

    public void clickExitButtonFromMyLists() {
        waitForElementAndClick(By.xpath(EXIT_BUTTON_FROM_MY_LISTS), "Cannot find exit from article button", 15);
    }
}
