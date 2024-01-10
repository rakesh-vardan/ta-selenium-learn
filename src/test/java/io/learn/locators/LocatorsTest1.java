package io.learn.locators;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest1 extends BaseTest {

    @Test
    public void test1() {
        driver.get("file:///Users/Rakesh_Budugu/locator-file1.html");

        boolean elementById = driver.findElement(By.id("username")).isDisplayed();
        System.out.println("Locator strategy ID is working? : " + elementById);

        boolean elementByName = driver.findElement(By.name("password")).isDisplayed();
        System.out.println("Locator strategy NAME is working? : " + elementByName);

        boolean elementByClassName = driver.findElement(By.className("submit-btn")).isDisplayed();
        System.out.println("Locator strategy CLASS is working? : " + elementByClassName);

        boolean elementByLinkText = driver.findElement(By.linkText("Click for more information")).isDisplayed();
        System.out.println("Locator strategy LINK-TEXT is working? : " + elementByLinkText);

        boolean elementByPartialLinkText = driver.findElement(By.partialLinkText("information")).isDisplayed();
        System.out.println("Locator strategy PARTIAL-LINK-TEXT is working? : " + elementByPartialLinkText);

        boolean elementByXpath = driver.findElement(By.xpath("//button[@class='submit-btn']")).isDisplayed();
        System.out.println("Locator strategy XPATH is working? : " + elementByXpath);

        boolean elementByCSSSelector = driver.findElement(By.cssSelector(".submit-btn")).isDisplayed();
        System.out.println("Locator strategy CSS-SELECTOR is working? : " + elementByCSSSelector);

        boolean elementByTagName = driver.findElement(By.tagName("h1")).isDisplayed();
        System.out.println("Locator strategy TAG-NAME is working? : " + elementByTagName);
    }
}
