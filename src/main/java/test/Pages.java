package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arild on 1/15/2017.
 */
public class Pages {
    WebDriver driver;
    WebElement element;

    public static void selectNumberOfRooms(WebDriver driver ,String number){
        Select numberOfRooms = new Select(driver.findElement(By.id("package-rooms")));
        numberOfRooms.selectByValue(number);
        int selectedRooms = Integer.parseInt(driver.findElement(By.xpath(".//*[contains(@class,'rooms-container')]")).getAttribute("class").substring(28,29));
        System.out.println("You selected "+ selectedRooms + " rooms");
    }

    public static void selectRoomOccupancy(WebDriver driver,String adults, String children){
        List<WebElement> roomObjects = driver.findElements(By.xpath(".//div[@id='package-rooms']//div"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        for(int i = 0; i < roomObjects.size();i++){
            Select adultOptions = new Select(driver.findElement(By.xpath(".//div[@id='package-rooms']//div//select[@id='package-"+(i+1)+"-adults']")));
            adultOptions.selectByValue(adults);
            Select childrenOptions = new Select(driver.findElement(By.xpath(".//div[@id='package-rooms']//div//select[@id='package-"+(i+1)+"-children']")));
            childrenOptions.selectByValue(children);
        }

    }

    public static void clickFlightsTab(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 1);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("tab-flight-tab")));
        driver.findElement(By.id("tab-flight-tab")).click();
    }

    public static void departureAirport(WebDriver driver, String airport){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//label[@id='flight-origin-label']//span[@class='visuallyhidden']")).click();
        driver.findElement(By.id("flight-origin")).sendKeys(airport);
        myAiportPicker(driver,".//*[@class='results-item']//div[contains(@class,'suggestion')]//b").get(0).click();
    }

    public static void arrivalAirport(WebDriver driver, String airport){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//label[@id='flight-destination-label']//span[@class='visuallyhidden']")).click();
        driver.findElement(By.id("flight-destination")).sendKeys("HONO");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        myAiportPicker(driver,".//*[@class='results-item']//div[contains(@class,'suggestion')]//b").get(0).click();
    }


    private static List<WebElement> myAiportPicker(WebDriver driver,String xpath){
        List<WebElement> elements;
        elements = driver.findElements(By.xpath(xpath));

        return elements;
    }


    public static void pickDefaultDepartureDate(WebDriver driver){

        driver.findElement(By.id("flight-departing")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='flight-departing-wrapper']//button[contains(@class,'datepicker-cal-date')][not(contains(@class,'disabled'))]"));
        elements.get(3).click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }

}
