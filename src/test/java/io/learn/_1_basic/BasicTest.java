package io.learn._1_basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BasicTest {

    @Test
    public void firstTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev");

        System.out.println("Current page title is: " + driver.getTitle()); //Selenium
        System.out.println("Current page URL is: " + driver.getCurrentUrl()); //https://www.selenium.dev

        driver.close();
        driver.quit();
    }
}
