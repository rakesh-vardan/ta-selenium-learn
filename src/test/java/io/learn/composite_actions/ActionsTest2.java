package io.learn.composite_actions;

import io.learn.BaseTest;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest2 extends BaseTest {

    @Test
    public void testDragAndDrop() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions actions = new Actions(driver);

        WebElement draggableElement = driver.findElement(By.id("draggable"));

        int offSet = 100;
        Point originalLocation = draggableElement.getLocation();
        System.out.println("Original location: "+originalLocation);

        actions.dragAndDropBy(draggableElement, offSet, 0)
               .dragAndDropBy(draggableElement, 0, offSet)
               .dragAndDropBy(draggableElement, -offSet, 0)
               .dragAndDropBy(draggableElement, 0, -offSet)
               .build().perform();

        Point movedLocation = draggableElement.getLocation();
        System.out.println("Moved Location: " + movedLocation);

        assertThat(movedLocation).isEqualTo(originalLocation);
    }

    @Test
    public void testDragAndDrop2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions actions = new Actions(driver);

        WebElement sourceElement = driver.findElement(By.id("draggable"));
        WebElement destinationElement = driver.findElement(By.id("target"));

        Point originalLocation = sourceElement.getLocation();
        System.out.println("Original location: "+originalLocation);

        Point destinationLocation = destinationElement.getLocation();
        System.out.println("Final location: "+destinationLocation);

        actions.dragAndDrop(sourceElement, destinationElement).build().perform();

        assertThat(originalLocation).isNotEqualTo(destinationLocation);
    }

    @Test
    void testClickAndHold() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.tagName("canvas"));
        actions.moveToElement(canvas).clickAndHold();

        int numPoints = 10;
        int radius = 15;
        for (int i = 0; i <= numPoints; i++) {

            // code to draw a circle
            double angle = Math.toRadians((double) (360 * i) / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;

            actions.moveByOffset((int) x, (int) y);
        }
        actions.release(canvas).build().perform();
    }

    @Test
    void testCopyAndPaste() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Actions actions = new Actions(driver);

        WebElement inputText = driver.findElement(By.name("my-text"));
        WebElement textarea = driver.findElement(By.name("my-textarea"));

        // logic to identify the respective button key, if the OS is Mac/others
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;

        actions.sendKeys(inputText, "hello world") // write the initial text in first text box
                .keyDown(modifier) // press the Control button
                .sendKeys(inputText, "a") // performs selecting the whole text (CTRL + A)
                .sendKeys(inputText, "c") // performs copying the text (CTRL + C)
                .sendKeys(textarea, "v") // pastes the copied text (CTRL + V)
                .build().perform();

        assertThat(inputText.getAttribute("value"))
                .isEqualTo(textarea.getAttribute("value"));
    }

    @Test
    void testScrollBy() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = "window.scrollBy(0, 1000);";
        js.executeScript(script);
    }

    // alternative for previous method
    @Test
    void testScrollBy2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        Actions actions = new Actions(driver);

        actions.scrollByAmount(0, 1000).build().perform();
    }

    @Test
    void testScrollIntoView() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement lastElement = driver.findElement(By.cssSelector("p:last-child"));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastElement);
    }


    @Test
    void testColorPicker() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is: " + initColor);

        // reference for color codes - https://www.color-hex.com/color/ff0000
        // code to get the hexa-decimal representation of color RED
        Color red = new Color(255, 0, 0, 1);
        String redHex = red.asHex(); // #ff0000

        String script = String.format("arguments[0].setAttribute('value', '%s');", redHex);
        js.executeScript(script, colorPicker);

        String finalColor = colorPicker.getAttribute("value");
        System.out.println("The final color is: " + finalColor);

        assertThat(finalColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(finalColor)).isEqualTo(red);
    }
}
