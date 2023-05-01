package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
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
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Switch_Role extends BaseTest{
    private WebDriver driver;
    private String firstName, lastName, adminEmail, userEmail, userPassword, adminPassword ;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private UserAddressPageObject userAddressPage;
    private UserMyProductReviewPageObject userMyProductReviewPage;
    private UserRewardPointPageObject userRewardPointPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;

    @Parameters("browser") //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName);

        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        userEmail = "abc2204@gmail.com";
        userPassword = "123456";
        adminEmail = "admin@yourstore.com";
        adminPassword = "admin";
    }

    @Test
    public void Role_01_User() {
        System.out.println("User_02_Login - Step 01: Click on login link");
        userLoginPage = userHomePage.clickOnLoginLink();

        //Login as user role
        userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

        System.out.println("User_02_Login - Step 04: Verify homepage displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void Role_02_Admin() {
        userHomePage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
    }

    @Test
    public void Role_01_User_To_Admin() {
        System.out.println("User_02_Login - Step 01: Click on login link");
        userLoginPage = userHomePage.clickOnLoginLink();

        //Login as user role
        userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

        System.out.println("User_02_Login - Step 04: Verify homepage displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        //Home page -> customer infor
        userCustomerInfoPage = userHomePage.openCustomerInfoPage(driver);

        //Customer infor click logout link -> home page
        userHomePage = userCustomerInfoPage.clickOnLogoutLinkAtUser(driver);

        //user home page -> open admin page -> login page (admin)
        userHomePage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

        //Login as Admin Role
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        //dashboard page -> click logout -> admin login page
        adminLoginPage = adminDashboardPage.clickOnLogoutLinkAtAdmin(driver);
    }

    @Test
    public void Role_02_Admin_To_User() {
        //Login page (admin) -> open portal url -> home page (user)
        adminLoginPage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        //home page -> login page (user)
        System.out.println("User_02_Login - Step 01: Click on login link");
        userLoginPage = userHomePage.clickOnLoginLink();

        //Login as user role
        userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

        System.out.println("User_02_Login - Step 04: Verify homepage displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
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