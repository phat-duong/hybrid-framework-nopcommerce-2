package com.nopcommerce.user;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_DRY {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(),
                "First name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(),
                "Last name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Email is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("testing");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("automation#gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("testing");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
//        driver.findElement(By.xpath("//a[text()='Log out']")).click();
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("testing");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("testing");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("testing");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123457");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
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
