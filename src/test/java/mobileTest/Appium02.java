package mobileTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.AssertJUnit.*;

public class Appium02 {

    @Test
    public void test01() throws MalformedURLException, InterruptedException {

         /* DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:deviceName", "Nyz");*/


        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Moto");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\app-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);

        System.out.println("App kuruldu");

        //driver.findElement(By.id("com.browserstack.addnumber:id/num1")).sendKeys("10");
        driver.findElement(AppiumBy.id("com.browserstack.addnumber:id/num1")).sendKeys("50");
        driver.findElement(AppiumBy.id("com.browserstack.addnumber:id/num2")).sendKeys("20");

        driver.findElement(By.id("com.browserstack.addnumber:id/addBtn")).click();

        String actualResult = driver.findElement(By.id("com.browserstack.addnumber:id/sampleLabel")).getText();
        System.out.println("actualResult = " + actualResult);
        assertEquals("Answer: 70",actualResult);
        assertTrue(actualResult.contains("70"));
    }

    @Test
    public void test02() throws MalformedURLException, InterruptedException {

        AndroidDriver driver = getDriver();

        Thread.sleep(5000);
        //App Name is visible
        assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text=\"AddNumber\"]")).isDisplayed());

        // Hello World! is visible
        assertTrue(driver.findElement(By.id("com.browserstack.addnumber:id/sampleLabel")).isDisplayed());

        //Field 1
        assertTrue(driver.findElement(By.id("com.browserstack.addnumber:id/num1")).isDisplayed());

        // Field 2
        assertTrue(driver.findElement(By.id("com.browserstack.addnumber:id/num2")).isDisplayed());

        // ADD button is visible
        assertTrue(driver.findElement(By.id("com.browserstack.addnumber:id/addBtn")).isDisplayed());



    }
    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Moto");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\app-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);

        return driver;
    }
}
