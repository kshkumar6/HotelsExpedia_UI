package com.hotels.qa.test;

import com.hotels.qa.base.TestBase;
import com.hotels.qa.pages.HomePage;
import com.hotels.qa.pages.SearchResultPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchSortAndWriteHotelResults extends TestBase {

    HomePage homePage=new HomePage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @BeforeMethod
    public void setUp() throws InterruptedException {
        initialization();
    }

    @Test
    public void enterTextAndSearch() throws InterruptedException {
        homePage.acceptCookieOnScreen();
        homePage.searchText("London");
        homePage.enterCheckInDate();
        homePage.enterCheckOutDate();
        homePage.closeDateWindow();
        homePage.searchTextButton();
        searchResultPage.sortPrice();
        searchResultPage.slideValues(8);
        searchResultPage.getHotelNameLink();
        searchResultPage.getHotelPriceLink();
        searchResultPage.getCombinedNameAndPriceList();
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}


