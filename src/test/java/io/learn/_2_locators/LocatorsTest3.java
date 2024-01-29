package io.learn._2_locators;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorsTest3 extends BaseTest {

    String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @Test
    public void testDisabledField() {
        driver.get(URL);
        WebElement disabledElement = driver.findElement(By.name("my-disabled"));
        assertThat(disabledElement.isDisplayed()).isTrue();
        assertThat(disabledElement.isEnabled()).isFalse(); //SRP - Single Responsibility Principle
    }

    @Test
    public void testReadOnlyField() {
        driver.get(URL);
        WebElement readOnlyElement = driver.findElement(By.name("my-readonly"));
        assertThat(readOnlyElement.isDisplayed()).isTrue();
        assertThat(readOnlyElement.isEnabled()).isTrue();
    }

    @Test
    public void testCssSelectorAdvanced() {
        driver.get(URL);
        WebElement selectedCheckBox = driver
                .findElement(By.cssSelector("input[type='checkbox']:checked"));
        assertThat(selectedCheckBox.isDisplayed()).isTrue(); //true
        assertThat(selectedCheckBox.isSelected()).isTrue(); //true
        selectedCheckBox.click();
        assertThat(selectedCheckBox.isSelected()).isFalse();

        WebElement unSelectedCheckBox = driver
                .findElement(By.cssSelector("input[type='checkbox']:not(:checked)"));
        assertThat(unSelectedCheckBox.isDisplayed()).isTrue(); //true
        assertThat(unSelectedCheckBox.isSelected()).isFalse(); //false
        unSelectedCheckBox.click();
        assertThat(unSelectedCheckBox.isSelected()).isTrue();
    }

    @Test
    public void testXPathLocatorAdvanced() {
        driver.get(URL);
        WebElement checkedRadioButton = driver
                .findElement(By.xpath("//input[@type='radio' and @checked]"));
        assertThat(checkedRadioButton.isDisplayed()).isTrue();
        assertThat(checkedRadioButton.isSelected()).isTrue();
        checkedRadioButton.click(); //not working - need to use JavascriptExecutor
        assertThat(checkedRadioButton.isSelected()).isFalse();

        WebElement uncheckedRadioButton = driver
                .findElement(By.xpath("//input[@type='radio' and not(@checked)]"));
        assertThat(uncheckedRadioButton.isDisplayed()).isTrue();
        assertThat(uncheckedRadioButton.isSelected()).isFalse();
        uncheckedRadioButton.click();
        assertThat(uncheckedRadioButton.isSelected()).isTrue();
    }

    @Test
    public void testXPathLocatorAdvancedUsingJSE() {
        driver.get(URL);

        WebElement uncheckedRadioButton = driver
                .findElement(By.xpath("//input[@type='radio' and not(@checked)]"));
        assertThat(uncheckedRadioButton.isDisplayed()).isTrue();
        assertThat(uncheckedRadioButton.isSelected()).isFalse();

        //using JavaScriptExecutor to click on the radio button - solution to above example
        JavascriptExecutor jse = (JavascriptExecutor) driver; //type casting in Java
        jse.executeScript("arguments[0].click()", uncheckedRadioButton);

        assertThat(uncheckedRadioButton.isSelected()).isTrue();
    }

    @Test
    public void testTextBox() {
        driver.get(URL);
        WebElement textBox = driver.findElement(By.name("my-text"));
        String text = "Hello, some text";
        textBox.sendKeys(text);
        assertThat(textBox.getAttribute("value")).isEqualTo(text);

        textBox.clear();
        assertThat(textBox.getAttribute("value")).isEmpty();
    }

    @Test
    public void testUploadFile() throws IOException {
        driver.get(URL);

        // identify file upload element
        WebElement fileUploadElement = driver.findElement(By.name("my-file"));

        // create temp file using Java code
        Path tempFile = Files.createTempFile("tempFiles", ".tmp");
        String filePath = tempFile.toAbsolutePath().toString();
        System.out.println("File name: " + filePath);

        // upload file
        fileUploadElement.sendKeys(filePath); //need to give the full path of the file

        // submit the form
        driver.findElement(By.tagName("form")).submit();

        // assertions
        assertThat(driver.getCurrentUrl()).isNotEqualTo(URL);
        assertThat(driver.getCurrentUrl()).contains("/submitted-form.html");
        assertThat(driver.findElement(By.cssSelector("h1[class='display-6']")).getText())
                .isEqualTo("Form submitted");
    }

    @Test
    public void testSlider() {
        driver.get(URL);
        WebElement sliderElement = driver.findElement(By.name("my-range"));
        String initialValue = sliderElement.getAttribute("value");
        System.out.println("Initial value of slider position: " + initialValue);

        for(int i = 0; i < 5; i++) {
            sliderElement.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = sliderElement.getAttribute("value");
        System.out.println("End value of slider position: " + endValue);
        assertThat(initialValue).isNotEqualTo(endValue);

        for(int i = 0; i < 10; i++) {
            sliderElement.sendKeys(Keys.ARROW_LEFT);
        }
    }
}
