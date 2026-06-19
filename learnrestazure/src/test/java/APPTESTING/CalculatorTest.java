//package APPTESTING;
//
//import static org.testng.Assert.assertEquals;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.Test;
//
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
//
//public class CalculatorTest {
//
//    AndroidDriver driver;
//
//    // Constructor
//    public CalculatorTest(AndroidDriver driver) {
//        this.driver = driver;
//    }
//
//    // Locators
//    By one = AppiumBy.id("com.coloros.calculator:id/digit_1");
//    By two = AppiumBy.id("com.coloros.calculator:id/digit_2");
//    By three = AppiumBy.id("com.coloros.calculator:id/digit_3");
//
//    By add = AppiumBy.id("com.coloros.calculator:id/op_add");
//    By sub = AppiumBy.id("com.coloros.calculator:id/op_sub");
//    By div = AppiumBy.id("com.coloros.calculator:id/op_div");
//    By equal = AppiumBy.id("com.coloros.calculator:id/eq");
//
//    // Result TextView (Inspect your calculator to confirm this ID)
//    By result = AppiumBy.id("com.coloros.calculator:id/result");
//
//    @Test
//    public void additionTest() {
//
//        driver.findElement(one).click();
//        driver.findElement(add).click();
//        driver.findElement(two).click();
//        driver.findElement(equal).click();
//
//        String actual = driver.findElement(result).getText();
//
//        assertEquals(actual, "3");
//    }
//
//    @Test
//    public void subtractionTest() {
//
//        driver.findElement(three).click();
//        driver.findElement(sub).click();
//        driver.findElement(one).click();
//        driver.findElement(equal).click();
//
//        String actual = driver.findElement(result).getText();
//
//        assertEquals(actual, "2");
//    }
//
//    @Test
//    public void divisionTest() {
//
//        driver.findElement(two).click();
//        driver.findElement(div).click();
//        driver.findElement(one).click();
//        driver.findElement(equal).click();
//
//        String actual = driver.findElement(result).getText();
//
//        assertEquals(actual, "2");
//    }
//}