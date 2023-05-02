
package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Common_01_Register_End_User extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName;
    public static String existingEmail, password;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;


    @Parameters("browser")
    @BeforeTest(description = "Create new common user for all Classes test")
    public void Register(String browserName) {
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

        driver.quit();
    }


//    @AfterTest
//    public void afterClass() {
//        driver.quit();
//    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}