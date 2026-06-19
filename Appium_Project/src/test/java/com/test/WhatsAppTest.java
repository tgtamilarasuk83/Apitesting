package com.test;

import java.net.URL;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class WhatsAppTest {

    public static void main(String[] args) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setCapability("platformName", "Android");
        options.setCapability("automationName", "UiAutomator2");
        options.setCapability("deviceName", "Android");
        options.setCapability("udid", "10MFABF8XT000AA");

        AndroidDriver driver =
                new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(5000);

        // Open WhatsApp
        driver.findElement(
                By.xpath("//android.widget.TextView[@content-desc='WhatsApp']"))
                .click();

        Thread.sleep(5000);

        // Open 3rd chat
        driver.findElement(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc='Swipe down to reveal additional actions']/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout"))
                .click();

        Thread.sleep(3000);

        // Enter message
        driver.findElement(AppiumBy.id("com.whatsapp:id/entry"))
                .sendKeys("Hi mental");

        Thread.sleep(2000);

        // Click Send button
        driver.findElement(
                By.xpath("//android.widget.ImageButton[@content-desc='Send']"))
                .click();

        System.out.println("Message sent successfully");

        Thread.sleep(5000);

        driver.quit();
    }
}