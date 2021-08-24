package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private String appiumURL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_MOBILE_WEB = "mobileWeb";
    private static final String PLATFORM_ANDROID = "android";

    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(appiumURL);
        if (isAndroid()) {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if (isMW()) {
            return new ChromeDriver(getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver");
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Courses\\Mobile Automation\\AppiumAutomation\\apks\\org.wikipedia.apk");
        return capabilities;
    }

    private boolean isPlatform(String myPlatform) {
        String platform = getPlatformVar();
        return myPlatform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private ChromeOptions getMWChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("height", 640);
        deviceMetrics.put("width", 360);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }
}
