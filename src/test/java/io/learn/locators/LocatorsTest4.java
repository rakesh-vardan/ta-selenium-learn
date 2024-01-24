package io.learn.locators;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorsTest4 extends BaseTest {

    String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @Test
    public void selectList() {
        driver.get(URL);
        WebElement dropDown = driver.findElement(By.name("my-select"));

        Select select = new Select(dropDown);
        String optionLabel = "Three";
//        select.selectByVisibleText(optionLabel);
//        select.selectByIndex(3);
        select.selectByValue("3");
        assertThat(select.getFirstSelectedOption().getText())
                .isEqualTo(optionLabel);
    }

    @Test
    public void selectDataListWithSearch() {
        driver.get(URL);

        WebElement dataList = driver.findElement(By.name("my-datalist"));
        dataList.click();

        WebElement option = driver
                .findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getAttribute("value");
        dataList.sendKeys(optionValue);

        assertThat(optionValue).isEqualTo("New York");
    }
}
