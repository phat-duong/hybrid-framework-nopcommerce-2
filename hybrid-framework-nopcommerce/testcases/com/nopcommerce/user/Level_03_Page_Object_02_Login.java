package com.nopcommerce.user;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_02_Login{
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        homePage = new UserHomePageObject(driver);

        firstName = "automation";
        invalidEmail = "abc#$gmail.com";
        existingEmail = "abc" + generateFakeNumber() + "@gmail.com";
        notFoundEmail = "abc" + generateFakeNumber() + "@vn.com";
        lastName = "testing";
        password = "123456";

        System.out.println("Precondition - Step 01: Click on register link");
        homePage.clickOnRegisterLink();
        //Click register link -> nhảy qua trang Register -> khởi tạo trang Register
        registerPage = new UserRegisterPageObject(driver);

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
//        registerPage.clickOnLogoutLink();

        //Click Logout no se quay ve trang HomePage
//        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void Login_01_Empty_Data() {
        System.out.println("Login_01 - Step 01: Click on login link");
        homePage.clickOnLoginLink();

        System.out.println("Login_01 - Step 02: Click on login button");
        //Từ trang Home - click on login link -> qua trang login -> phải khởi tạo trang login
        loginPage = new UserLoginPageObject(driver);
        loginPage.clickOnLoginButton();

        System.out.println("Login_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email() {
        System.out.println("Login_02 - Step 01: Click on login link");
        homePage.clickOnLoginLink();

        System.out.println("Login_02 - Step 02: Input invalid email");
        loginPage = new UserLoginPageObject(driver);
        loginPage.inputIntoEmailTextbox(invalidEmail);

        System.out.println("Login_02 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Wrong email");
    }
    @Test
    public void Login_03_Email_Not_Found() {

        System.out.println("Login_03 - Step 01: Click on login link");
        homePage.clickOnLoginLink();

        System.out.println("Login_03 - Step 02: Input not found email");
        loginPage = new UserLoginPageObject(driver);
        loginPage.inputIntoEmailTextbox(notFoundEmail);

        System.out.println("Login_03 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_03 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }
    @Test
    public void Login_04_Existing_Email_Empty_Password() {
        System.out.println("Login_04 - Step 01: Click on login link");
        homePage.clickOnLoginLink();

        System.out.println("Login_04 - Step 02: Input email and password");
        loginPage = new UserLoginPageObject(driver);
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
        homePage.clickOnLoginLink();

        System.out.println("Login_05 - Step 02: Input email and password");
        loginPage = new UserLoginPageObject(driver);
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
        homePage.clickOnLoginLink();

        System.out.println("Login_06 - Step 02: Input email and password");
        loginPage = new UserLoginPageObject(driver);
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("Login_06 - Step 03: Click on login button");
        loginPage.clickOnLoginButton();

        System.out.println("Login_06 - Step 04: Verify homepage displayed");
        homePage = new UserHomePageObject(driver);
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
