package test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    void searchForFlights(){
        myFindElementById("tab-flight-tab").click();
        driver.findElement(By.xpath(".//label[@id='flight-origin-label']//span[@class='visuallyhidden']")).click();
        myFindElementById("flight-origin").sendKeys("DEN");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        myAiportPicker(".//*[@class='results-item']//div[contains(@class,'suggestion')]//b").get(0).click();
        driver.findElement(By.xpath(".//label[@id='flight-destination-label']//span[@class='visuallyhidden']")).click();
        myFindElementById("flight-destination").sendKeys("HONO");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        myAiportPicker(".//*[@class='results-item']//div[contains(@class,'suggestion')]//b").get(0).click();
        myFindElementById("flight-departing").click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        myDatePicker(".//*[@id='flight-departing-wrapper']//button[contains(@class,'datepicker-cal-date')][not(contains(@class,'disabled'))]").get(3).click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        myFindElementById("search-button").click();

        //Assert
        Assert.assertEquals("The search result does not match the search",
                            "Select your departure to Honolulu",
                             driver.findElement(By.xpath(".//header[@id='titleBar']//span[@class='title-city-text']")).getText());
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


    public WebElement myFindElementById(String id){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(id)));

        return element;
    }

    public List<WebElement> myDatePicker(String xpath){
        List<WebElement> elements;
       elements = driver.findElements(By.xpath(xpath));

       return elements;
    }

    public List<WebElement> myAiportPicker(String xpath){
        List<WebElement> elements;
        elements = driver.findElements(By.xpath(xpath));

        return elements;
    }
//
//    public void mySendKeys(String elementId, String keys){
//       WebElement element = myFindElementById(elementId);
//       driver.switchTo().activeElement()
//
//
//    }

}
