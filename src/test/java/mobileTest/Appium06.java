package mobileTest;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Appium01 {

    @Test
    public void test01() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:deviceName", "Nyz");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        System.out.println("Telefon ile irtibat kuruldu");


        driver.get("https://www.google.com");

    }
}
