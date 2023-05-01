package com.nopcommerce.user;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_01_Register{
    //Declare (khai báo)
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String firstName, lastName, emailAddress, password ;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

        //Init (khởi tạo)
        homePage = new UserHomePageObject(driver);
        registerPage = new UserRegisterPageObject(driver);

        firstName = "automation";
        emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
        lastName = "testing";
        password = "123456";
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Click on register button");
        //Click register link -> nhảy qua trang Register
//        registerPage = new UserRegisterPageObject(driver);
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 03: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Email is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox("automation#gmail.com");
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("RegisterPage - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("RegisterPage - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 04: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        System.out.println("RegisterPage - Step 05: Click on log out link");
        registerPage.clickOnLogoutLink();
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("RegisterPage - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("123");
        registerPage.inputToConfirmPasswordTextbox("123");

        System.out.println("RegisterPage - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        System.out.println("HomePage - Step 01: Click on register link");
        homePage.clickOnRegisterLink();

        System.out.println("RegisterPage - Step 02: Input into required fields");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox("123457");

        System.out.println("RegisterPage - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        System.out.println("RegisterPage - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");
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
