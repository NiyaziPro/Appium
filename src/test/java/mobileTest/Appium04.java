package mobileTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.*;

public class Appium04 {


    @Test
    public void test01() throws MalformedURLException, InterruptedException {
        AndroidDriver driver = getDriver();

        Thread.sleep(2000);
        WebElement clear = driver.findElement(By.id("com.google.android.calculator:id/clr"));
        WebElement paranthesis = driver.findElement(By.id("com.google.android.calculator:id/parens"));
        WebElement percent = driver.findElement(By.id("com.google.android.calculator:id/op_pct"));
        WebElement divide = driver.findElement(By.id("com.google.android.calculator:id/op_div"));
        WebElement multiply = driver.findElement(By.id("com.google.android.calculator:id/op_mul"));
        WebElement minus = driver.findElement(By.id("com.google.android.calculator:id/op_sub"));
        WebElement plus = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        WebElement equals = driver.findElement(By.id("com.google.android.calculator:id/eq"));
        WebElement delete = driver.findElement(By.id("com.google.android.calculator:id/del"));
        WebElement point = driver.findElement(By.id("com.google.android.calculator:id/dec_point"));

        WebElement num0 = driver.findElement(By.id("com.google.android.calculator:id/digit_0"));
        WebElement num1 = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
        WebElement num2 = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
        WebElement num3 = driver.findElement(By.id("com.google.android.calculator:id/digit_3"));
        WebElement num4 = driver.findElement(By.id("com.google.android.calculator:id/digit_4"));
        WebElement num5 = driver.findElement(By.id("com.google.android.calculator:id/digit_5"));
        WebElement num6 = driver.findElement(By.id("com.google.android.calculator:id/digit_6"));
        WebElement num7 = driver.findElement(By.id("com.google.android.calculator:id/digit_7"));
        WebElement num8 = driver.findElement(By.id("com.google.android.calculator:id/digit_8"));
        WebElement num9 = driver.findElement(By.id("com.google.android.calculator:id/digit_9"));

        num2.click();
        num5.click();
        multiply.click();
        num5.click();
        String expectedResult = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
        equals.click();
        String actualResult = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();
        assertEquals(actualResult,expectedResult);



    }

    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Appium");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\Calculator.apk");
        //androidOptions.setOrientation(ScreenOrientation.LANDSCAPE);


        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }
}



