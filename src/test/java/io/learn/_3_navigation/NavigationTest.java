package io.learn._3_navigation;

import io.learn.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationTest extends BaseTest {

    @Test
    public void verifyNavigation() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        String firstPage = baseUrl + "navigation1.html";
        String secondPage = baseUrl + "navigation2.html";
        String thirdPage = baseUrl + "navigation3.html";

        driver.get(firstPage);

        driver.navigate().to(secondPage);
        driver.navigate().to(thirdPage);
        driver.navigate().back();
        driver.navigate().forward();
        assertThat(driver.getCurrentUrl()).isEqualTo(thirdPage);

        driver.navigate().refresh();
        assertThat(driver.getCurrentUrl()).isEqualTo(thirdPage);
    }
}
