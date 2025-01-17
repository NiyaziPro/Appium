package mobileTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class BrowserTest {

    @Test
    public void browserTest() throws MalformedURLException, InterruptedException {
        AndroidDriver driver = getDriver();

        Thread.sleep(2000);
        //driver.context("NATIVE_APP");
        System.out.println("driver.getContext() = " + driver.getContext());
        driver.get("https://www.amazon.com");

        System.out.println("driver.getContext() = " + driver.getContext());
        Set<String> contextHandles = driver.getContextHandles();
        System.out.println("driver.getContextHandles() = " + contextHandles);

        for (String context : contextHandles) {
            if (context.contains("CHROMIUM") || contextHandles.contains("WEBVIEW")) {
                driver.context(context);
                System.out.println("context = " + context);
                break;
            }
        }
        Thread.sleep(3000);

        //driver.findElement(By.id("nav-button-avatar")).click();
        driver.context("NATIVE_APP");
        Thread.sleep(2000);
        System.out.println("driver.getContext() = " + driver.getContext());
    }

    @Test
    public void test02() throws InterruptedException, MalformedURLException {
        AndroidDriver driver = getDriver();

        Thread.sleep(2000);
        //driver.context("NATIVE_APP");
        System.out.println("driver.getContext() = " + driver.getContext());
        driver.get("https://www.amazon.com");

        System.out.println("driver.getContext() = " + driver.getContext());
        Set<String> contextHandles = driver.getContextHandles();
        System.out.println("driver.getContextHandles() = " + contextHandles);

        for (String context : contextHandles) {
            if (context.contains("CHROMIUM") || contextHandles.contains("WEBVIEW")) {
                driver.context(context);
                System.out.println("context = " + context);
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.id("nav-search-keywords")).sendKeys("Appium", Keys.ENTER);

        List<WebElement> list = driver.findElements(By.cssSelector("h2"));
        System.out.println("list.size() = " + list.size());
        for (WebElement result : list) {
            System.out.println("result = " + result.getText() + "\n");
            
        }

    }

    private AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName("UiAutomator2");
        androidOptions.setPlatformName("Android");
        androidOptions.setPlatformVersion("13");
        androidOptions.setDeviceName("Hello Appium");
        androidOptions.withBrowserName("chrome");
        androidOptions.setChromedriverExecutable("C:\\IntelliJ IDEA\\Appium\\drivers\\chromedriver.exe");
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), androidOptions);
    }
}
