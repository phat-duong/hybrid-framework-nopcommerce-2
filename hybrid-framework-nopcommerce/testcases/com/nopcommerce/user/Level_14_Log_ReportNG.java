package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_14_Log_ReportNG extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserAddressPageObject addressPage;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserRewardPointPageObject rewardPointPageObject;


    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);

        firstName = "automation";
        invalidEmail = "abc#$gmail.com";
        existingEmail = "abc" + generateFakeNumber() + "@gmail.com";
        notFoundEmail = "abc" + generateFakeNumber() + "@vn.com";
        lastName = "testing";
        password = "123456";
    }

    @Test
    public void User_01_Register() {
        log.info("User_01_Register - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();

        log.info("User_01_Register - Step 02: Input into required fields");
        log.info("User_01_Register - Step 02: Input into firstName fields with value is:' " + firstName + "'");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(existingEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        log.info("User_01_Register - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        log.info("User_01_Register - Step 04: Verify success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        log.info("User_01_Register - Step 05: Click on log out link");
//        homePage = registerPage.clickOnLogoutLink();
    }

    @Test
    public void User_02_Login() {
        System.out.println("Login_06 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_06 - Step 02: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("Login_06 - Step 03: Click on login button");
        homePage = loginPage.clickOnLoginButton();

        System.out.println("Login_06 - Step 04: Verify 'My Account' link at homepage is displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());

        System.out.println("Login_06 - Step 05: Navigate to 'My Account' page");
        customerInfoPage = homePage.clickOnMyAccountLink();

        System.out.println("Login_06 - Step 06: Verify 'Custom Infor' page is displayed");
        verifyTrue(customerInfoPage.isCustomerInfoPageDisplayed());
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