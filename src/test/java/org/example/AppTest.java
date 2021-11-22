package org.example;

import io.appium.java_client.android.AndroidDriver;
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
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    static AndroidDriver driver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {


        //Created object of DesiredCapabilities class
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //The kind of mobile device or emulator to use - iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4 etc
        //Find your device name by running command 'adb devices' from command prompt
       capabilities.setCapability("deviceName", "device");

        //Which mobile OS platform to use - iOS, Android, or FirefoxOS
        capabilities.setCapability("platformName", "Android");
//                 caps.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9.0");

        //Java package of the Android app you want to run- Ex: com.example.android.myApp
        //For Android calculator app, package name is 'com.android.calculator2'
        capabilities.setCapability("appPackage", "com.android.calculator2");

        //Activity name for the Android activity you want to launch from your package
        //For Android calculator app, Activity name is 'com.android.calculator2.Calculator'
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

//        capabilities.setCapability("browserName", "android");
//        capabilities.setCapability("browserVersion", "8.1");

        // HashMap<String, Object> selenoidOptions = new HashMap<String, Object>();
        // selenoidOptions.put("enableVNC", true);
        // selenoidOptions.put("enableVideo", false);
        // selenoidOptions.put("sessionTimeout", "120s");
        // selenoidOptions.put("browserName", "android");
        // selenoidOptions.put("browserVersion", "8.1");
        // capabilities.setCapability("selenoid:options", selenoidOptions);

        // Initialize the driver object with the URL to Appium Server and pass capabilities
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void Sum() {

        System.out.println("Calculate sum of two numbers");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Locate elements using By.name() to enter data and click +/= buttons
        driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_2")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        driver.findElement(By.id("com.android.calculator2:id/digit_3")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_4")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        driver.findElement(By.id("com.android.calculator2:id/digit_5")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_6")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        driver.findElement(By.id("com.android.calculator2:id/digit_7")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        // Get the result text
        WebElement sumOfNumbersEle = driver.findElement(By.className("android.widget.EditText"));
        String sumOfNumbers = sumOfNumbersEle.getText();

        //verify if result is 3
        Assert.assertTrue(sumOfNumbers.endsWith("3"));
    }

    @AfterClass
    public static void End() {
        driver.quit();
    }
}
