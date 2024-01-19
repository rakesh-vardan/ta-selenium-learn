package io.learn.locators;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsTest2 extends BaseTest {

    String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    //1. find element
    //2. perform an action

    @Test(enabled = false)
    public void testById() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        //by id
        driver.findElement(By.id("my-text-id")).sendKeys("hello, this is my text");

/*        By byId = By.id("my-text-id");
        WebElement element = driver.findElement(byId);
        element.sendKeys("hello, this is my text");*/

/*        Assert.assertEquals(2, 1); //TestNG
        Assertions.assertThat(1).isEqualTo(1); //AssertJ*/

        System.out.println("Test");
    }

    //by id
    @Test
    public void testById1() {
        driver.get(URL);
        WebElement elementById = driver.findElement(By.id("my-text-id"));
        Assert.assertEquals(elementById.getAttribute("type"), "text");
        Assert.assertEquals(elementById.getAttribute("myprop"), "myvalue");
    }

    //by name
    @Test
    public void testByName() {
        driver.get(URL);
        WebElement elementByName = driver.findElement(By.name("my-text"));
        Assert.assertEquals(elementByName.getAttribute("type"), "text");
        Assert.assertEquals(elementByName.getAttribute("myprop"), "myvalue");
    }

    //by link text
    @Test
    public void testByLinkText() {
        driver.get(URL);
        WebElement elementByLinkText = driver.findElement(By.linkText("Return to index"));
        Assert.assertEquals(elementByLinkText.getTagName(), "a");

        WebElement elementByPartialLinkText = driver.findElement(By.partialLinkText("index"));
        Assert.assertEquals(elementByPartialLinkText.getTagName(), "a");
    }

    //by class name
    @Test
    public void testByClassName() {
        driver.get(URL);
        List<WebElement> elements = driver.findElements(By.className("form-control"));
        Assert.assertEquals(elements.size(), 9);
        Assert.assertEquals(elements.get(0).getAttribute("name"), "my-text");
    }

    //by tag name
    @Test
    public void testByTagName() {
        driver.get(URL);
        WebElement elementByTagName = driver.findElement(By.tagName("textarea"));
        Assert.assertEquals(elementByTagName.getAttribute("rows"), "3");
    }

    //by xpath
    @Test
    public void testByXpath() {
        driver.get(URL);
        WebElement elementByXpath = driver.findElement(By.xpath("//input[@type='hidden']"));
        Assert.assertFalse(elementByXpath.isDisplayed()); //false
    }


    //by css
    @Test
    public void testByCss() {
        driver.get(URL);
        WebElement elementByCss = driver.findElement(By.cssSelector("input[type='hidden']"));
        Assert.assertFalse(elementByCss.isDisplayed()); //false
    }
}
