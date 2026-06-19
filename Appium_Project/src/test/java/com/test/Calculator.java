package com.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Calculator {

    AndroidDriver driver;
    WebDriverWait wait;

    // Buttons
    By clearBtn = By.id("com.coloros.calculator:id/clr");
    By digit1 = By.id("com.coloros.calculator:id/digit_1");
    By digit2 = By.id("com.coloros.calculator:id/digit_2");
    By digit3 = By.id("com.coloros.calculator:id/digit_3");
    By digit4 = By.id("com.coloros.calculator:id/digit_4");

    By addBtn = By.id("com.coloros.calculator:id/op_add");
    By subBtn = By.id("com.coloros.calculator:id/op_sub");
    By mulBtn = By.id("com.coloros.calculator:id/op_mul");
    By divBtn = By.id("com.coloros.calculator:id/op_div");
    By eqBtn  = By.id("com.coloros.calculator:id/eq");

    By resultLocator = By.id("com.coloros.calculator:id/result");

    @BeforeClass
    public void setup() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android");
        options.setUdid("MZWOVGZ5CQ9D6TLB");

        options.setAppPackage("com.coloros.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");

        options.setNoReset(true);
        options.setCapability("ignoreHiddenApiPolicyError", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void resetApp() {
        driver.findElement(clearBtn).click();
    }

    // ---------------- ADDITION ----------------
    @Test(priority = 1)
    public void addition() {

        driver.findElement(digit1).click();
        driver.findElement(addBtn).click();
        driver.findElement(digit2).click();
        driver.findElement(eqBtn).click();

        assertEquals(getResult(), "3");
    }

    // ---------------- SUBTRACTION ----------------
    @Test(priority = 2)
    public void subtraction() {

        driver.findElement(digit3).click();
        driver.findElement(subBtn).click();
        driver.findElement(digit1).click();
        driver.findElement(eqBtn).click();

        assertEquals(getResult(), "2");
    }

    // ---------------- MULTIPLICATION ----------------
    @Test(priority = 3)
    public void multiplication() {

        driver.findElement(digit2).click();
        driver.findElement(mulBtn).click();
        driver.findElement(digit3).click();
        driver.findElement(eqBtn).click();

        assertEquals(getResult(), "6");
    }

    // ---------------- DIVISION ----------------
    @Test(priority = 4)
    public void division() {

        driver.findElement(digit4).click();
        driver.findElement(divBtn).click();
        driver.findElement(digit2).click();
        driver.findElement(eqBtn).click();

        assertEquals(getResult(), "2");
    }

    // ---------------- COMPLEX EXPRESSION ----------------
    @Test(priority = 5)
    public void multipleOperations() {

        // 1 + 2 * 3 (depends on calculator precedence)
        driver.findElement(digit1).click();
        driver.findElement(addBtn).click();
        driver.findElement(digit2).click();
        driver.findElement(mulBtn).click();
        driver.findElement(digit3).click();
        driver.findElement(eqBtn).click();

        System.out.println("Complex Result = " + getResult());
    }

    // ---------------- CLEAR VALIDATION ----------------
    @Test(priority = 6)
    public void clearFunctionality() {

        driver.findElement(digit1).click();
        driver.findElement(digit2).click();
        driver.findElement(clearBtn).click();

        String result = getResult();

        System.out.println("After Clear = " + result);

        assertTrue(
            result.equalsIgnoreCase("No result") ||
            result.equalsIgnoreCase("") ||
            result.equalsIgnoreCase("0"),
            "Unexpected clear state: " + result
        );
    }

    // ---------------- RESULT HANDLER ----------------
    private String getResult() {

        WebElement resultElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultLocator)
        );

        String result = resultElement.getText();

        if (result == null || result.isEmpty()) {
            result = resultElement.getAttribute("contentDescription");
        }

        return result == null ? "" : result.trim();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}