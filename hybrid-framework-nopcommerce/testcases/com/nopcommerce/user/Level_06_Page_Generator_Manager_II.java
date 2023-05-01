package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

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

public class Level_06_Page_Generator_Manager_II extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;

    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        //1
        homePage = new UserHomePageObject(driver);

        firstName = "automation";
        invalidEmail = "abc#$gmail.com";
        existingEmail = "abc" + generateFakeNumber() + "@gmail.com";
        notFoundEmail = "abc" + generateFakeNumber() + "@vn.com";
        lastName = "testing";
        password = "123456";

        System.out.println("Precondition - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();

        System.out.println("Precondition - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(existingEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Precondition - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("Precondition - Step 04: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        System.out.println("Precondition - Step 05: Click on log out link");
//        homePage = registerPage.clickOnLogoutLink();
    }

    @Test
    public void Login_01_Empty_Data() {
        System.out.println("Login_01 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_01 - Step 02: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email() {
        System.out.println("Login_02 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_02 - Step 02: Input invalid email");
        loginPage.inputIntoEmailTextbox(invalidEmail);

        System.out.println("Login_02 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    @Test
    public void Login_03_Email_Not_Found() {

        System.out.println("Login_03 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_03 - Step 02: Input not found email");
        loginPage.inputIntoEmailTextbox(notFoundEmail);

        System.out.println("Login_03 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_03 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }
    @Test
    public void Login_04_Existing_Email_Empty_Password() {
        System.out.println("Login_04 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_04 - Step 02: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox("");

        System.out.println("Login_04 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_04 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_05_Existing_Email_Incorrect_Password() {
        System.out.println("Login_05 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_05 - Step 02: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox("123457");

        System.out.println("Login_05 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_05 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }
    @Test
    public void Login_06_Valid_Email_Password() {
        System.out.println("Login_06 - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Login_06 - Step 02: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("Login_06 - Step 03: Click on login button");
        homePage = loginPage.clickOnLoginButton();

        System.out.println("Login_06 - Step 04: Verify homepage displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
