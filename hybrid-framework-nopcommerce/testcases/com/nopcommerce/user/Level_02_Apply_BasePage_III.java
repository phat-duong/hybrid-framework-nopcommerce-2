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

//nếu extends thì k cần khai báo, k cần khởi tạo BasePage
public class Level_02_Apply_BasePage_III extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");


        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='FirstName-error']"),
                "First name is required.");
        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='LastName-error']"),
                "Last name is required.");
        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='Email-error']"), "Email is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        sendkeyToElementXpath(driver, "//input[@id='Email']", "automation#gmail.com");
        sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");


        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementTextXpath(driver, "//div[@class='result']"), "Your registration completed");

        waitForElementClickableXpath(driver, "//a[text()='Log out']");
        clickToElementXpath(driver, "//a[text()='Log out']");
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementTextXpath(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElementXpath(driver, "//input[@id='Password']", "123");
        sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        waitForElementClickableXpath(driver, "//a[text()='Register']");
        clickToElementXpath(driver, "//a[text()='Register']");

        sendkeyToElementXpath(driver, "//input[@id='FirstName']", "automation");
        sendkeyToElementXpath(driver, "//input[@id='LastName']", "testing");
        sendkeyToElementXpath(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElementXpath(driver, "//input[@id='Password']", "123456");
        sendkeyToElementXpath(driver, "//input[@id='ConfirmPassword']", "123457");

        waitForElementClickableXpath(driver, "//button[@id='register-button']");
        clickToElementXpath(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementTextXpath(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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