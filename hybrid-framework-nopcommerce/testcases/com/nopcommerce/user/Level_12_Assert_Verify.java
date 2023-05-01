package com.nopcommerce.user;

import org.testng.annotations.*;

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

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

//thêm annotation Listeners để khi fail nhiều chỗ thì nó báo fail bao nhiêu chỗ
//thêm annotation Listenes ở class testcase này, hoặc
//tối ưu thì thêm tag Listeners ở file xml

//@Listeners(commons.MethodListener.class)
public class Level_12_Assert_Verify extends BaseTest{
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
        System.out.println("User_01_Register - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();

        System.out.println("User_01_Register - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(existingEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("User_01_Register - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("User_01_Register - Step 04: Verify success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
//        verifyTrue(registerPage.getRegisterSuccessMessage().startsWith("123"));

        System.out.println("User_01_Register - Step 05: Click on log out link");
//        homePage = registerPage.clickOnLogoutLink();
//        verifyTrue(registerPage.getRegisterSuccessMessage().startsWith("456"));
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