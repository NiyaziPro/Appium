package mobileTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class InterviewTest_Calculator {

    /*
    Mobile Automation Engineer Assignment

Overview:
Complete the following mobile automation test. This assignment can be done over the course of 2 days.
Please make commits as often as possible so we can see the time you spent and please do not make all
your changes in one big commit. We will evaluate the code and time spent along with how your commits
are split up.
Email your solution as soon as you have completed the challenge or the time is up.

Key Goals:
● As a Customer when I perform a calculation, I want the answer displayed completely after
pressing the = button and evaluated by rounding up to the 4th decimal place.
○ Action
■ GIVEN the user opens the calculator app
■ WHEN the user  adds 23+95
■ AND takes the square root of that sum
■ AND multiply it by -1
○ Expected Result
■ THEN assert that the answer is correctly displayed as
-10.862782780491200215723891499337473741120122
■ THEN asset that the value rounded up to the 4th decimal place is ​-10.8628

● Please use Appium to write and run your tests. You can use any language and platform (Android
or iOS) of your choice, but we prefer that you write your tests in Java. Avoid the usage of "canned
tools" such as the Selenium IDE app extension, Katalon, QTP, etc. We expect you to write the
tests manually yourself.
● Please place your code in a private Github repository and be prepared to give one of our
reviewers access. Commit each step of your process so we can follow your thought process.
● The project should be able to be run on Windows or OSX with no manual dependency fetching
required. Please make good use of gradle or maven.


● Please include the device emulator/simulator setup in a read.me file so the reviewer has no
questions about running your solution.
● You may need to rotate the emulator/simulator to complete certain actions.
     */

    AndroidDriver driver;

    @Test
    public void testCalculatorApp() throws MalformedURLException, InterruptedException {


        Thread.sleep(2000);

        WebElement plus = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        WebElement equals = driver.findElement(By.id("com.google.android.calculator:id/eq"));
        WebElement squareRoot = driver.findElement(By.id("com.google.android.calculator:id/op_sqrt"));

        clickDigits("23");
        plus.click();
        clickDigits("95");
        equals.click();
        String sumResult = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();

        squareRoot.click();
        clickDigits(sumResult);
        equals.click();

        Thread.sleep(3000);

        driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
        driver.findElement(By.id("com.google.android.calculator:id/op_sub")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

        driver.rotate(ScreenOrientation.LANDSCAPE);
        Thread.sleep(2000);
        String sqrtResult = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();
        driver.rotate(ScreenOrientation.PORTRAIT);

        sqrtResult = sqrtResult.replace("−", "-");

        BigDecimal result = new BigDecimal(sqrtResult);

        BigDecimal roundedResult = result.setScale(42, RoundingMode.UP);
        System.out.println("Yuvarlanmış 42 basamak: " + roundedResult);

        BigDecimal expectedResult = new BigDecimal("-10.862780491200215723891493374737411201226662");

        Assert.assertEquals(roundedResult, expectedResult);

        BigDecimal roundedToFour = roundedResult.setScale(4, RoundingMode.UP);
        System.out.println("Yuvarlanmış 4 basamak: " + roundedToFour);

        double calculatedResult = roundedToFour.doubleValue();
        Assert.assertEquals(calculatedResult, -10.8628);


    }

    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("MyAndroid");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\Calculator.apk");
        //androidOptions.setOrientation(ScreenOrientation.LANDSCAPE);


        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }

    // Method: Sayıyı al ve her basamağa tek tek tıkla
    public void clickDigits(String number) throws MalformedURLException {
        // Sayıdaki her bir basamağı tek tek al
        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);

            // Her basamağı string olarak al ve tıklanabilir bir element bul
            WebElement digitElement = getDigitElement(String.valueOf(digit));

            // Sayının basamağını tıkla
            digitElement.click();
        }
    }

    // Method: Sayıdaki bir basamağa göre ilgili elementin bulunması
    public WebElement getDigitElement(String digit) throws MalformedURLException {
        WebElement element = null;

        // Sayıdaki basamağa göre ilgili butonları bulmak
        switch (digit) {
            case "0":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_0"));
                break;
            case "1":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
                break;
            case "2":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
                break;
            case "3":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_3"));
                break;
            case "4":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_4"));
                break;
            case "5":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_5"));
                break;
            case "6":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_6"));
                break;
            case "7":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_7"));
                break;
            case "8":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_8"));
                break;
            case "9":
                element = driver.findElement(By.id("com.google.android.calculator:id/digit_9"));
                break;
            default:
                throw new IllegalArgumentException("Geçersiz basamak: " + digit);
        }

        return element;
    }

}
