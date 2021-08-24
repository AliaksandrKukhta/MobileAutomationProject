package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase {

    protected RemoteWebDriver driver;
    protected Platform platform;

    @Before
    @Step("Create driver")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        rotateScreenPortrait();
        openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Close driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to Portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() do nothing for another platform");
        }
    }

    @Step("Open Wikipedia page on mobile Web")
    protected void openWikiWebPageForMobileWeb() {
        if (platform.isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() do nothing for another platform");
        }
    }
}
