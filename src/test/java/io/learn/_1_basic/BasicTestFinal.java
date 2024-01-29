package io.learn._1_basic;

import io.learn.BaseTest;
import org.testng.annotations.Test;

public class BasicTestFinal extends BaseTest {

    @Test
    public void firstTest() {
        driver.get("https://www.selenium.dev");
        System.out.println("Current page title is: " + driver.getTitle()); //Selenium
        System.out.println("Current page URL is: " + driver.getCurrentUrl()); //https://www.selenium.dev
    }
}
