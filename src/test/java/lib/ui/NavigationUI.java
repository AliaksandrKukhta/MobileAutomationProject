package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject{
    public NavigationUI (RemoteWebDriver driver){
        super(driver);
    }

    private static final String
            MY_LIST_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']",
            LIST = "org.wikipedia:id/item_title";

    public void clickMyListButton() {
        waitForElementAndClick(By.xpath(MY_LIST_BUTTON), "Cannot find android.widget.ImageButton", 15);
    }

    public void openList(){
        waitForElementAndClick(By.id(LIST), "Cannot find My Java List", 10);
    }

    public void lestSwipe(){
        leftSwipe(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article");
    }
}
