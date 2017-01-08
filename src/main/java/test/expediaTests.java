package test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        driver.get(baseUrl);
    }

    @AfterEach
    void tearDown(){
//        driver.quit();
    }


    @Test
    void verifySelectedRoomsAfterOccupancy(){
        Select numberOfRooms = new Select(driver.findElement(By.id("package-rooms")));
        numberOfRooms.selectByValue("3");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        int selectedRooms = Integer.parseInt(driver.findElement(By.xpath(".//*[contains(@class,'rooms-container')]")).getAttribute("class").substring(28,29));
        System.out.println("You selected "+ selectedRooms + " rooms");
        List<WebElement> roomObjects = driver.findElements(By.xpath(".//div[@id='package-rooms']//div"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        for(int i = 0; i < roomObjects.size();i++){
           Select adultOptions = new Select(driver.findElement(By.xpath(".//div[@id='package-rooms']//div//select[@id='package-"+(i+1)+"-adults']")));
            adultOptions.selectByValue("2");
           Select childrenOptions = new Select(driver.findElement(By.xpath(".//div[@id='package-rooms']//div//select[@id='package-"+(i+1)+"-children']")));
            childrenOptions.selectByValue("3");
        }


        Assert.assertEquals("The number of selected rooms does not match",3,selectedRooms);
    }

    @Test
    void test2(){

    }

    @Test
    void test3(){

    }

    @Test
    void test4(){

    }

    @Test
    void test5(){

    }

}
