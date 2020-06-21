package com.hotels.qa.pages;

import com.hotels.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomePage extends TestBase {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void acceptCookieOnScreen(){
        WebElement cookieAccept = driver.findElement(By.className("cookie-policy-banner-accept"));
        cookieAccept.click();
    }

    public void searchText(String textToBeSearched) throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("qf-0q-destination"));
        searchBox.sendKeys(textToBeSearched);
        Thread.sleep(2000);
        WebElement autoSuggest= driver.findElement(By.className("autosuggest-category-result"));
        autoSuggest.click();
    }

    public void enterCheckInDate(){
        WebElement checkInDate= driver.findElement(By.id("qf-0q-localised-check-in"));
        checkInDate.clear();
        String checkInDateValueToBeEntered= formatter.format( LocalDate.now().plusMonths(3));
        checkInDate.sendKeys(checkInDateValueToBeEntered);
    }
    public void enterCheckOutDate(){
        WebElement checkOutDate = driver.findElement(By.id("qf-0q-localised-check-out"));
        checkOutDate.clear();
        String checkoutDateValueToBeEntered = formatter.format(LocalDate.now().plusMonths(3).plusDays(3));
        checkOutDate.sendKeys(checkoutDateValueToBeEntered);
    }
    public void closeDateWindow(){
        WebElement closeDateWidget = driver.findElement(By.className("widget-overlay-close"));
        closeDateWidget.click();
    }
    public void searchTextButton(){
        WebDriverWait wait = new WebDriverWait(driver,10000);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"hds-marquee\"]/div[2]/div/div/form/div[4]/button"));
        searchButton.submit();
        wait.until(ExpectedConditions.invisibilityOf(searchButton));
    }
}
