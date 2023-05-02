
package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;
import java.util.Set;

public class Common_01_Register_Cookie extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, existingEmail, password;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    public static Set<Cookie> loggedCookies;


    @Parameters("browser")
    @BeforeTest(description = "Create new common user for all Classes test") //Xài beforetest, k xài before suite
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

        System.out.println("Precondition - Step 06: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("Precondition - Step 07: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("Precondition - Step 08: Click on login button");
        homePage = loginPage.clickOnLoginButton();


        loggedCookies = homePage.getAllCookies(driver);
        for (Cookie cookie : loggedCookies){
            System.out.println("Cookies at Common class: " + cookie);
        }
    }


    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}