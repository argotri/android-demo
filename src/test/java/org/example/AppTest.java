package org.example;

import static org.junit.Assert.assertTrue;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    static WebDriver driver;
    static AppiumServiceBuilder serviceBuilder;
    static AppiumDriverLocalService server;

    @BeforeClass
    public static void setUp() throws MalformedURLException {

        serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();

        //Created object of DesiredCapabilities class
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //The kind of mobile device or emulator to use - iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4 etc
        //Find your device name by running command 'adb devices' from command prompt
//        capabilities.setCapability("deviceName", "WKZLMJLB7L8TR8SW");

        //Which mobile OS platform to use - iOS, Android, or FirefoxOS
        capabilities.setCapability("platformName", "Android");

        //Java package of the Android app you want to run- Ex: com.example.android.myApp
        //For Android calculator app, package name is 'com.android.calculator2'
        capabilities.setCapability("appPackage", "com.android.calculator2");

        //Activity name for the Android activity you want to launch from your package
        //For Android calculator app, Activity name is 'com.android.calculator2.Calculator'
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        // Initialize the driver object with the URL to Appium Server and pass capabilities
        driver = new RemoteWebDriver(server.getUrl(), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void Sum() {

        System.out.println("Calculate sum of two numbers");
        //Locate elements using By.name() to enter data and click +/= buttons
        driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_2")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        // Get the result text
        WebElement sumOfNumbersEle = driver.findElement(By.className("android.widget.EditText"));
        String sumOfNumbers = sumOfNumbersEle.getText();

        //verify if result is 3
        Assert.assertTrue(sumOfNumbers.endsWith("3"));
    }

    @AfterClass
    public static void End() {
        try {
            driver.quit();
        } catch (Exception ign) {}
        server.stop();
    }
}
