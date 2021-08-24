package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String
            LOGIN_BUTTON=".//*[@href=\"/w/index.php?title=Special:UserLogin&returnto=Java_(programming_language)&warning=mobile-frontend-watchlist-purpose&campaign=mobile_watchPageActionCta&returntoquery=article_action%3Dwatch\"]",
            LOGIN_INPUT="wpName",
            PASSWORD_INPUT="wpPassword",
            SUBMIT_BUTTON="wpLoginAttempt";

    public void clickAuthButton(){
        waitForElementPresent(By.xpath(LOGIN_BUTTON), "Cannot find auth button", 10);
        waitForElementAndClick(By.name(LOGIN_INPUT), "Cannot click auth button", 5);
    }

    public void EnterLoginAndPassword(String login, String password){
        waitForElementAndSendKeys(By.name(LOGIN_INPUT), login, "Cannot find and put a login  to the login page", 10);
        waitForElementAndSendKeys(By.name(PASSWORD_INPUT), password, "Cannot find and put a password  to the login page", 10);
    }

    public void submitForm(){
        waitForElementAndClick(By.id(SUBMIT_BUTTON), "Cannot find and click submit button", 10);
    }
}
