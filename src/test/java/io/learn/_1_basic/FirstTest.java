package io.learn._1_basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();
//        WebDriver driver = new EdgeDriver();
//        WebDriver driver = new SafariDriver();
        driver.get("https://www.selenium.dev");

        System.out.println("Current page title is: " + driver.getTitle()); //Selenium
        System.out.println("Current page URL is: " + driver.getCurrentUrl()); //https://www.selenium.dev

        driver.close();
        driver.quit();
    }
}
