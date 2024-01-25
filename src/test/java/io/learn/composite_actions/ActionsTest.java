package io.learn.composite_actions;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest extends BaseTest {

    @Test
    public void testContextClickAndDoubleClick() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");

        // using actions class to perform right click/context click
        Actions actions = new Actions(driver);
        WebElement rightClickElement = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(rightClickElement).build().perform();

        //assertion
        WebElement menu2 = driver.findElement(By.id("context-menu-2"));
        assertThat(menu2.isDisplayed()).isTrue();

        // using actions class to perform double click
        WebElement doubleClickElement = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(doubleClickElement).build().perform();

        //assertion
        WebElement menu3 = driver.findElement(By.id("context-menu-3"));
        assertThat(menu3.isDisplayed()).isTrue();
    }

    @Test
    public void testMouseHover() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");

        // load the page in full screen
        driver.manage().window().maximize();

        // defining actions class for mouse-hover action
        Actions actions = new Actions(driver);
        WebElement compassImage = driver.findElement(By
                .xpath("//img[@src='img/compass.png']"));
        actions.moveToElement(compassImage).build().perform();

        // using relative locator to identify the text
        WebElement caption = driver.findElement(RelativeLocator
                .with(By.tagName("div")).near(compassImage));

        //asserting the caption text
        assertThat(caption.getText()).isEqualTo("Compass");
    }

    @Test
    public void testMouseHoverComplex() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");

        //img[@src='img/compass.png']
        //img[@src='img/calendar.png']
        //img[@src='img/award.png']
        //img[@src='img/landscape.png']

        for(String imageName : imageList) {
            String xpath = String.format("//img[@src='img/%s.png']", imageName);
            WebElement image = driver.findElement(By.xpath(xpath));
            actions.moveToElement(image).build().perform();

            WebElement caption = driver.findElement(RelativeLocator
                    .with(By.tagName("div")).near(image));
            assertThat(caption.getText()).isEqualToIgnoringCase(imageName);
        }
    }
}
