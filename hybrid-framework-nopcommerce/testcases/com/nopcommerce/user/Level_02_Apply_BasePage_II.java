package com.nopcommerce.user;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_II {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    //Declare (khai bao)
    BasePage basePage;
    //BasePage: Class
    //basePage: Object

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        //initial (khoi tao)
        //che giau di viec khoi tao cua 1 doi tuong
        basePage = BasePage.getBasePageObject();

        emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
//        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");
        //k thấy việc findElement
        //k thấy click ở đây

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");


        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='FirstName-error']"),
                "First name is required.");
        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='LastName-error']"),
                "Last name is required.");
        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");

        basePage.sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        basePage.sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Email']", "automation#gmail.com");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");


        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");

        basePage.sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        basePage.sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementTextXpath(driver, "//div[@class='result']"), "Your registration completed");

        basePage.waitForElementClickableXpath(driver, "//a[text()='Log out']");
        basePage.clickToElementXpath(driver, "//a[text()='Log out']");
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");

        basePage.sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        basePage.sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementTextXpath(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");

        basePage.sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        basePage.sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElementXpath(driver, "//input[@id='Password']", "123");
        basePage.sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123");

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        basePage.waitForElementClickableXpath(driver, "//a[text()='Register']");
        basePage.clickToElementXpath(driver, "//a[text()='Register']");

        basePage.sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        basePage.sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        basePage.sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123457");

        basePage.waitForElementClickableXpath(driver, "//button[@id='register-button']");
        basePage.clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementTextXpath(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
