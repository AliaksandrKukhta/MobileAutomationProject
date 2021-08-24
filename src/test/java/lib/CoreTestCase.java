package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;
    protected Platform platform;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        platform = Platform.getInstance();
        driver = platform.getDriver();
        rotateScreenPortrait();
        openWikiWebPageForMobileWeb();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() do nothing for another platform");
        }
    }

    protected void openWikiWebPageForMobileWeb() {
        if (platform.isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() do nothing for another platform");
        }
    }
}
