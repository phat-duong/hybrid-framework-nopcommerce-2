package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_19_Browser_Capabilities extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, existingEmail, password;
    private String day, month, year;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;

    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getUserHomePage(driver);
        showBrowserConsoleLogs(driver);

        firstName = "automation";
        existingEmail = "abc" + generateFakeNumber() + "@gmail.com";
        lastName = "testing";
        password = "123456";

        day = "10";
        month = "August";
        year = "1998";
    }

    @Test
    public void User_01_Register() {
        log.info("User_01_Register - Step 01: Click on register link");
        registerPage = homePage.clickOnRegisterLink();
        showBrowserConsoleLogs(driver); //dùng hàm này khi chuyển trang

        registerPage.clickOnRadioButtonByLabel(driver, "Male");

        log.info("User_01_Register - Step 02: Input into required fields");
        log.info("User_01_Register - Step 02: Input into firstName fields with value is:' " + firstName + "'");
//        registerPage.inputToFirstnameTextbox(firstName);
//        registerPage.inputToLastnameTextbox(lastName);
//        registerPage.inputToEmailTextbox(existingEmail);
//        registerPage.inputToPasswordTextbox(password);
//        registerPage.inputToConfirmPasswordTextbox(password);

        //Pattern Object: Viết thành 1 hàm dùng chung
        registerPage.inputToTextboxByID(driver, "FirstName", firstName);
        registerPage.inputToTextboxByID(driver, "LastName", lastName);
        registerPage.inputToTextboxByID(driver, "Email", existingEmail);
        registerPage.inputToTextboxByID(driver, "Password", password);
        registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

        log.info("User_01_Register - Step 04: Select dropdown");
        registerPage.selectToDropdownByName(driver, "DateOfBirthDay", day);
        registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
        registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

        registerPage.clickOnCheckboxByLabel(driver, "Newsletter");

        log.info("User_01_Register - Step 05: Click on register button");
//        registerPage.clickOnRegisterButton();

        registerPage.clickOnButtonByText(driver, "Register");
        showBrowserConsoleLogs(driver);

        log.info("User_01_Register - Step 06: Verify success message displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        log.info("User_01_Register - Step 07: Click on log out link");
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
//        homePage = loginPage.clickOnLoginButton();
        loginPage.clickOnButtonByText(driver, "Log in");
        homePage = PageGeneratorManager.getUserHomePage(driver);
        showBrowserConsoleLogs(driver);

        System.out.println("Login_06 - Step 04: Verify 'My Account' link at homepage is displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_My_Account() {
        System.out.println("My Account - Step 01: Navigate to 'My Account' page");
        customerInfoPage = homePage.clickOnMyAccountLink();

        System.out.println("My Account - Step 02: Verify 'Custom Infor' page is displayed");
        Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());

        System.out.println("My Account - Step 03: Verify 'First Name' value is correctly");
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);

        System.out.println("My Account - Step 04: Verify 'Last Name' value is correctly");
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);

        System.out.println("My Account - Step 05: Verify 'Email' value is correctly");
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), existingEmail);

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