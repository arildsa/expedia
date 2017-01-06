package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Arild on 1/5/2017.
 */
public class expediaTests {

    WebDriver driver;
    String baseUrl;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.gecko.driver", "c:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://www.expedia.com";
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void test1(){
        driver.get(baseUrl);
    }

}
