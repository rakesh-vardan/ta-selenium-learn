package io.learn.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

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
