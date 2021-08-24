package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject (RemoteWebDriver driver){
        this.driver=driver;
    }

    public String assertElementPresent(By by, String attribute){
        return driver.findElement(by).getAttribute(attribute);
    }

//    public void moveAndClickAction(WebElement by, int timeOfSwipe) {
//        Dimension size = driver.manage().window().getSize();
//        int x = (int) (size.width * 0.9);
//        int y = (int) (size.height * 0.1);
//        TouchAction action = new TouchAction(driver);
//        action.press(by)
//                .waitAction(timeOfSwipe)
//                .release()
//                .perform();
//    }

    public WebElement waitForElementPresent(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> assertElementsHasText(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement assertElementHasText(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement assertElementHasTextById(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndClearAndSendKeys(By by, String value, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.sendKeys(value);
        return element;
    }

    public boolean elementIsDisplayed(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver){
            Dimension size = driver.manage().window().getSize();
            int x = (int) size.width / 2;
            int firstY = (int) (size.height * 0.8);
            int lastY = (int) (size.height * 0.2);
            TouchAction action = new TouchAction((AppiumDriver)driver);
            action
                    .press(PointOption.point(x, firstY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, lastY))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() do nothing for another platform");
        }
    }

    public void swipeQiuck() {
        swipeUp(500);
    }

    public void swipeToElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwipes = 0;
        int size = driver.findElements(by).size();
        while (size == 0) {
            if (alreadySwipes > maxSwipes) {
                waitForElementPresent(by, "Cannot find article after swipes. + \n" + errorMessage, 10);
                return;
            }
            swipeQiuck();
            ++alreadySwipes;
        }
    }

    public void leftSwipe(By by, String errorMessage){
        if (driver instanceof AppiumDriver){
            WebElement element = waitForElementPresent(by, errorMessage, 20);
            int leftX=element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY= element.getLocation().getY();
            int lowerY=upperY+element.getSize().getHeight();
            int middleY=(upperY+lowerY)/2;

            TouchAction action = new TouchAction((AppiumDriver)driver);
            action
                    .press(PointOption.point(rightX, middleY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
                    .moveTo(PointOption.point(leftX,middleY))
                    .release()
                    .perform();
        }else {
            System.out.println("Method leftSwipe() do nothing for another platform");
        }
    }

    public void scrollWebPage(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0,250)");
        }else {
            System.out.println("Method scrollWebPage() do nothing for another platform");
        }
    }

    public boolean isElementLocatedOnTheScreen(By by){
        int elementLocationByY=waitForElementPresent(by, "Cannot find element", 1).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Object jsResult=javascriptExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY=driver.manage().window().getSize().getHeight();
        return elementLocationByY<screenSizeByY;
    }

    public void scrollWebPageTillElementNotVisible(By by, String errorMessage, int maxSwipes){
        int alreadySwipe=0;
        WebElement webElement = waitForElementPresent(by, errorMessage, 15);

        while (!isElementLocatedOnTheScreen(by)){
            scrollWebPage();
            ++alreadySwipe;
            if(alreadySwipe>maxSwipes){
                Assert.assertTrue(errorMessage, webElement.isDisplayed());
            }
        }
    }
}
