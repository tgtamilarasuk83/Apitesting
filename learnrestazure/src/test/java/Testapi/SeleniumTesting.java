package Testapi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTesting {

	 @Test
    	public void seenium() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://jsonplaceholder.typicode.com/");
           String currenurl = driver.getCurrentUrl();
           System.out.println(currenurl);
           Assert.assertTrue(currenurl.contains("Jsonplaceholder"));
        } finally {
            driver.quit();
        }
    
}}