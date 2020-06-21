package com.hotels.qa.pages;

import com.hotels.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends TestBase {
    List<WebElement> elementList = new ArrayList<>();
    List<WebElement> priceList  = new ArrayList<>();
    List<String> HotelNameList = new ArrayList<>();
    List<String> HotelPriceList= new ArrayList<>();


    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    public void sortPrice() {
        WebDriverWait wait = new WebDriverWait(driver,10000);
        Actions actions = new Actions(driver);
        WebElement priceOption = driver.findElement(By.xpath("//*[@id=\"enhanced-sort\"]/li[5]/a"));
        actions.moveToElement(priceOption).perform();
        WebElement highToLowLink  = driver.findElement(By.xpath("//*[@id=\"sort-submenu-price\"]/li[1]/a"));
        highToLowLink.click();
    }
    public void slideValues(int numberOfClicks){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement leftSlider = driver.findElement(By.xpath("//*[@id=\"filter-guest-rating\"]/div[2]/div/div[2]/div[2]"));
        for (int i = 1; i <= numberOfClicks ; i++) {
            leftSlider.sendKeys(Keys.ARROW_RIGHT);
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("window.scrollBy(0,1500)", "");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getHotelNameLink(){
        elementList = driver.findElements(By.className("property-name-link"));
        for(WebElement el:elementList){
            HotelNameList.add(el.getText());
        }
    }

    public void getHotelPriceLink(){
        priceList = driver.findElements(By.className("price-link"));
        for(WebElement el:priceList){
            HotelPriceList.add(el.getText());
        }
    }
    public void getCombinedNameAndPriceList(){
        try {
            FileWriter writer = new FileWriter("./src/main/resources/result.csv");
            int count = 0;
            while (count < HotelNameList.size()&& count<10) {
                writer.write(HotelNameList.get(count) + "," + HotelPriceList.get(count).substring(1).replace(",",""));
                writer.write("\n\n");
                count++;
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }
}
