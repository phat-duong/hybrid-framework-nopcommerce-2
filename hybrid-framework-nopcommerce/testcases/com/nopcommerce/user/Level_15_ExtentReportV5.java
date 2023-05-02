package com.nopcommerce.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
import reportConfig.ExtentTestManagerV5;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_15_ExtentReportV5 extends BaseTest{
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
    public void User_01_Register(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "TC_01_Register");

        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();

        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 02: Input into required fields");
        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 02: Input into firstName fields with value is:' " + firstName + "'");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(existingEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 04: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed fail");

        ExtentTestManagerV5.getTest().log(Status.INFO, "User_01_Register - Step 05: Click on log out link");
        homePage = registerPage.clickOnLogoutLink();

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