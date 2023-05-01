package com.nopcommerce.user;

import org.testng.annotations.Test;

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

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Page extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password ;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserAddressPageObject addressPage;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserRewardPointPageObject rewardPointPage;


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
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        System.out.println("User_01_Register - Step 05: Click on log out link");
//        homePage = registerPage.clickOnLogoutLink();
    }

    @Test
    public void User_02_Login() {
        System.out.println("User_02_Login - Step 01: Click on login link");
        loginPage = homePage.clickOnLoginLink();

        System.out.println("User_02_Login - Step 02: Input email and password");
        loginPage.inputIntoEmailTextbox(existingEmail);
        loginPage.inputIntoPasswordTextbox(password);

        System.out.println("User_02_Login - Step 03: Click on login button");
        homePage = loginPage.clickOnLoginButton();

        System.out.println("User_02_Login - Step 04: Verify homepage displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }
    @Test
    public void User_03_Customer_Info() {
        customerInfoPage = homePage.clickOnMyAccountLink();
        Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
    }
    @Test
    public void User_04_Switch_Page() {
        //Knowledge của Page Object:
        //Một page A khi chuyển qua page B thì phải viết 1 hàm
        // (action: open, click,...: link/ button/ image/...) để mở page B lên
        // mặc dù cùng hàm nhưng nó nằm ở page khác nhau -> nếu viết ở từng page khác nhau
        // thì vi phạm nguyên tắc DRY

        //Customer info -> address
        addressPage = customerInfoPage.openAddressPage(driver);

        //Address -> my product review
        myProductReviewPage = addressPage.openMyProductReviewPage(driver);

        //My product review -> reward point
        rewardPointPage = myProductReviewPage.openRewardPointPage(driver);

        //reward point -> address
        addressPage = rewardPointPage.openAddressPage(driver);

        //address -> reward point
        rewardPointPage = addressPage.openRewardPointPage(driver);

        //reward point -> my product review
        myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
    }
    @Test
    public void User_05_Switch_Role() {

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