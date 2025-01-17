package mobileTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Appium05 {

    @Test
    public void test01() throws MalformedURLException, InterruptedException {

        /*
            1) API demos yuklenmis olacak
            2) title API DEMOS
            3) Prefence tiklyacagiz
            4) Preference from code
            5_ check box check edilecek
            6) switch actif olacak
            7)Edit text preference tikla
            8) pop up gorulecek
            9) text filede " APPIUM" yaz
            10) onayla
            11) check box check edilmis olacak
            12)swicth button active olacak
            13) Appium text box kayitli olmali
             */
        AndroidDriver driver = getDriver();

        Thread.sleep(3000);
        WebElement title = driver.findElement(By.xpath("//android.widget.TextView[@text=\"API Demos\"]"));
        Assert.assertTrue(title.isDisplayed());
        Assert.assertEquals(title.getText(), "API Demos");


        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Preference\"]")).click();


        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"5. Preferences from code\"]")).click();

        WebElement checkBox = driver.findElement(By.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[1]"));

        if (checkBox.getDomAttribute("checked").equals("false")) {
            checkBox.click();
        }


        WebElement switchPreference = driver.findElement(By.xpath("//android.widget.Switch[@resource-id=\"android:id/switch_widget\"]"));
        if (switchPreference.getDomAttribute("checked").equals("false")) {
            switchPreference.click();
        }

        WebElement editText = driver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[3]/android.widget.RelativeLayout"));

        editText.click();

        driver.findElement(By.id("android:id/edit")).sendKeys("Appium");

        Assert.assertTrue(driver.findElement(By.id("android:id/button1")).isDisplayed());
        driver.findElement(By.id("android:id/button1")).click();

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[1]")).getDomAttribute("checked"),"true");
        Assert.assertEquals(switchPreference.getDomAttribute("Checked"),"true");

    }

    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Appium");
        androidOptions.setApp("C:\\IntelliJ IDEA\\Appium\\apps\\ApiDemos-debug.apk");
        androidOptions.setFullReset(true);
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }
}
