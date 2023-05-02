package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

import java.util.Random;
import java.util.Set;

public class Level_16_Share_Data_C extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;


    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);

        existingEmail = Common_01_Register_End_User.existingEmail;
        password = Common_01_Register_End_User.password;

        System.out.println("Login_06 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_06 - Step 02: Set cookie and reload page");
        loginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
        for (Cookie cookie : Common_01_Register_Cookie.loggedCookies){
            System.out.println("Cookies at C class: " + cookie);
        }
        loginPage.refreshCurrentPage(driver);

        System.out.println("Login_06 - Step 03: Verify 'My Account' link at homepage is displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }


    @Test
    public void Search_01_Empty_Data() {

    }

    @Test
    public void Search_02_Relative_Product_Name() {

    }

    @Test
    public void Search_03_Absolute_Product_Name() {

    }

    @Test
    public void Search_04_Parent_Category() {

    }

    @Test
    public void Search_05_Incorrect_Manufacturer() {

    }

    @Test
    public void Search_06_Correct_Manufacturer() {

    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}