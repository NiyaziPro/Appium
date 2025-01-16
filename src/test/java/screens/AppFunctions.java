package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppFunctions {
    AndroidDriver driver;

    public AppFunctions() throws MalformedURLException {

        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.google.android.calculator:id/clr")
    public WebElement clear;

    @FindBy(id = "com.google.android.calculator:id/parens")
    public WebElement paranthesis;

    @FindBy(id = "com.google.android.calculator:id/op_pct")
    public WebElement percent;

    @FindBy(id = "com.google.android.calculator:id/op_div")
    public WebElement divide;

    @FindBy(id = "com.google.android.calculator:id/op_mul")
    public WebElement multiply;

    @FindBy(id = "com.google.android.calculator:id/op_sub")
    public WebElement minus;

    @FindBy(id = "com.google.android.calculator:id/op_add")
    public WebElement plus;

    @FindBy(id = "com.google.android.calculator:id/eq")
    public WebElement equals;

    @FindBy(id = "com.google.android.calculator:id/del")
    public WebElement delete;

    @FindBy(id = "com.google.android.calculator:id/dec_point")
    public WebElement point;

    @FindBy(id = "com.google.android.calculator:id/op_sqrt")
    public WebElement squareRoot;

    @FindBy(id = "com.google.android.calculator:id/digit_0")
    public WebElement digit_0;

    @FindBy(id = "com.google.android.calculator:id/digit_1")
    public WebElement digit_1;

    @FindBy(id = "com.google.android.calculator:id/digit_2")
    public WebElement digit_2;

    @FindBy(id = "com.google.android.calculator:id/digit_3")
    public WebElement digit_3;

    @FindBy(id = "com.google.android.calculator:id/digit_4")
    public WebElement digit_4;

    @FindBy(id = "com.google.android.calculator:id/digit_5")
    public WebElement digit_5;

    @FindBy(id = "com.google.android.calculator:id/digit_6")
    public WebElement digit_6;

    @FindBy(id = "com.google.android.calculator:id/digit_7")
    public WebElement digit_7;

    @FindBy(id = "com.google.android.calculator:id/digit_8")
    public WebElement digit_8;

    @FindBy(id = "com.google.android.calculator:id/digit_9")
    public WebElement digit_9;

    @FindBy(id = "com.google.android.calculator:id/result_final")
    public WebElement result;


    AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("MyAndroid");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\Calculator.apk");
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }

    public AppFunctions result() {
        this.result.getText();
        return this;
    }

    public AppFunctions multiply() {
        this.multiply.click();
        return this;
    }

    public AppFunctions divide() {
        this.divide.click();
        return this;
    }

    public AppFunctions equals() {
        this.equals.click();
        return this;
    }

    public AppFunctions plus() {
        this.plus.click();
        return this;
    }

    public AppFunctions minus() {
        this.minus.click();
        return this;
    }

    public AppFunctions squareRoot() {
        this.squareRoot.click();
        return this;
    }

    public void screenOrientationLandscape() {
        this.driver.rotate(ScreenOrientation.LANDSCAPE);
        waitForScreenRotation();
    }

    public void screenOrientationPortrait() {
        this.driver.rotate(ScreenOrientation.PORTRAIT);
        waitForScreenRotation();
    }

    private void waitForScreenRotation() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOf(result));
    }

    public AppFunctions clickDigits(String number) throws MalformedURLException {

        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            WebElement digitElement = getDigitElement(String.valueOf(digit));
            digitElement.click();
        }
        return this;
    }

    public WebElement getDigitElement(String digit) throws MalformedURLException {
        WebElement element = switch (digit) {
            case "0" -> digit_0;
            case "1" -> digit_1;
            case "2" -> digit_2;
            case "3" -> digit_3;
            case "4" -> digit_4;
            case "5" -> digit_5;
            case "6" -> digit_6;
            case "7" -> digit_7;
            case "8" -> digit_8;
            case "9" -> digit_9;
            default -> throw new IllegalArgumentException("Geçersiz basamak: " + digit);
        };

        return element;
    }

    ////////////////////////////////////////////////////////////////////////
    public String performOperation(AppFunctions appFunctions, String num1, String operator, String num2) throws MalformedURLException {
        appFunctions.clickDigits(num1);

        switch (operator) {
            case "+":
                appFunctions.plus();
                break;
            case "-":
                appFunctions.minus();
                break;
            case "*":
                appFunctions.multiply();
                break;
            case "/":
                appFunctions.divide();
                break;
            default:
                throw new IllegalArgumentException("Geçersiz Operatör: " + operator);
        }

        appFunctions.clickDigits(num2).equals();
        return appFunctions.result.getText();
    }

    public String performSquareRootOperation(AppFunctions appFunctions, String number, String subtrahend) throws MalformedURLException {
        appFunctions.squareRoot().clickDigits(number).equals().multiply().minus().clickDigits(subtrahend).equals();
        appFunctions.screenOrientationLandscape();
        String sqrtResult = appFunctions.result.getText();
        appFunctions.screenOrientationPortrait();
        return sqrtResult.replace("−", "-");
    }


    public void validateResult(String actualResult, String expectedString, int decimalPlaces) {
        BigDecimal result = new BigDecimal(actualResult);
        BigDecimal expectedResult = new BigDecimal(expectedString);
        BigDecimal roundedResult = result.setScale(42, RoundingMode.UP);
        Assert.assertEquals(roundedResult, expectedResult);
        BigDecimal roundedToFour = roundedResult.setScale(decimalPlaces, RoundingMode.UP);
        double calculatedResult = roundedToFour.doubleValue();
        Assert.assertEquals(calculatedResult, -10.8628);
    }
}