package mobileTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.*;

public class Appium03 {


    @Test
    public void test01() throws MalformedURLException, InterruptedException {
        AndroidDriver driver = getDriver();

        Thread.sleep(2000);
        driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("com.davemac327.gesture.tool:id/addButton")).click();

        Thread.sleep(2000);
        assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());

        Thread.sleep(2000);

        driver.findElement(By.id("com.davemac327.gesture.tool:id/gesture_name")).sendKeys("Hello Appium :)");

        assertEquals(driver.findElement(By.id("com.davemac327.gesture.tool:id/gesture_name")).getText(), "Hello Appium :)");


        assertFalse(driver.findElement(By.id("com.davemac327.gesture.tool:id/done")).isEnabled());

        driver.findElement(By.id("com.davemac327.gesture.tool:id/gestures_overlay")).click();

        assertTrue(driver.findElement(By.id("com.davemac327.gesture.tool:id/done")).isEnabled());

        driver.findElement(By.id("com.davemac327.gesture.tool:id/done")).click();

        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Hello Appium :)\"]")).isDisplayed());

    }

    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Appium");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\GestureTool.apk");
        androidOptions.setAppActivity("com.davemac327.gesture.tool.GestureBuilderActivity");
        androidOptions.setAppPackage("com.davemac327.gesture.tool");

        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }
}



