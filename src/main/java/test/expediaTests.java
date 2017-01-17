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
        driver.quit();
    }


    @Test
    void verifySelectedRoomsAfterOccupancy(){
        Pages.selectNumberOfRooms(driver,"3");
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Pages.selectRoomOccupancy(driver,"2","3");

        Assert.assertEquals("The number of selected rooms does not match",3,3);
    }

    @Test
    void searchForFlights(){
        Pages.clickFlightsTab(driver);
        Pages.departureAirport(driver, "DEN");
        Pages.arrivalAirport(driver,"HONO");
        Pages.pickDefaultDepartureDate(driver);
        myFindElementById("search-button").click();

        //Assert
        Assert.assertEquals("The search result does not match the search",
                            "Select your departure to Honolulu",
                             driver.findElement(By.xpath(".//header[@id='titleBar']//span[@class='title-city-text']")).getText());
    }

    @Test
    void searchForDisneyVacationsVerifyResult(){
        driver.findElement(By.xpath(".//*[@data-lobtab='opensearch']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//input[@id='opensearch-searchQuery']")).sendKeys("Disney Vacations");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        myFindElementById("search-button").click();
        List<WebElement> searchResults = driver.findElements(By.xpath(".//a[@class='flex-link']"));
        for(int i = 0; i < searchResults.size(); i++){
            String text = searchResults.get(i).getText();
            Assert.assertTrue("the search result does not contain Disney",text.contains("Disney"));
        }

    }

    @Test
    void test4(){

    }

    @Test
    void test5(){

    }


    public WebElement myFindElementById(String id){
        WebDriverWait wait = new WebDriverWait(driver, 1);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(id)));

        return element;
    }


}
