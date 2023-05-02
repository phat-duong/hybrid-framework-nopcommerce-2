package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_16_Share_Data_B extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;


    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);

        firstName = "automation";
        existingEmail = "abc" + generateFakeNumber() + "@gmail.com";
        lastName = "testing";
        password = "123456";

        log.info("Precondition - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();

        log.info("Precondition - Step 02: Input into required fields");
        log.info("Precondition - Step 02: Input into firstName fields with value is:' " + firstName + "'");
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(existingEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        log.info("Precondition - Step 03: Click on register button");
        registerPage.clickOnRegisterButton();

        log.info("Precondition - Step 04: Verify success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        log.info("Precondition - Step 05: Click on log out link");
//        homePage = registerPage.clickOnLogoutLink();

        System.out.println("Precondition - Step 06: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Precondition - Step 07: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("Precondition - Step 08: Click on login button");
        homePage = loginPage.clickOnLoginButton();
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