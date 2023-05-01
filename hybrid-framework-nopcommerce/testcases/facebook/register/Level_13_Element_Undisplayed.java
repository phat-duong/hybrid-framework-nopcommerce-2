package facebook.register;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

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

public class Level_13_Element_Undisplayed extends BaseTest{
    private WebDriver driver;
    private LoginPageObject loginPage;

    @Parameters({"browser", "url"}) //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName, pageUrl);

        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void TC_01_Verify_Element_Displayed() {
        loginPage.clickOnCreateNewAccountButton();

        //Nếu 1 hàm chỉ mong đợi để verify element displayed thôi - kết hợp cả wait + isDisplayed
        // waitForElementVisible
        // isElementDisplayed
        verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
    }

    @Test
    public void TC_02_Verify_Element_Undisplayed_In_DOM() {
        loginPage.refreshCurrentPage(driver);

        //Nếu như mình mong đợi 1 cái hàm vừa verify displayed/ undisplayed thì không kết hợp wait
        // isElementDisplayed

        loginPage.clickOnCreateNewAccountButton();
        loginPage.enterToEmailAddressTextbox("abc@gmail.com");

        //Verify True - mong đợi confirm email displayed (true)
        verifyTrue(loginPage.isEmailAddressConfirmTextboxDisplayed());

        loginPage.enterToEmailAddressTextbox("");
        loginPage.sleepInSecond(3);

        //Verify True - cho hàm trả về là undisplayed
        //Verify false - cho hàm trả về là displayed
        // Verify false - mong đợi confirm email undisplayed (false)
        verifyFalse(loginPage.isEmailAddressConfirmTextboxDisplayed());
    }

    @Test
    public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
        loginPage.clickOnIconClose();

        //Khi close cái form register đi thì confirm email k còn trong DOM nữa
        // nên hàm findElement sẽ bị fail vì không tìm thấy element
        // 1 - Nó sẽ chờ hết timeout của implicit: 30s
        // 2 - Nó sẽ đánh fail testcase tại step này luôn
        // 3 - K có chạy các step còn lại nữa

//		verifyFalse(loginPage.isEmailAddressConfirmTextboxDisplayed());

        verifyTrue(loginPage.isEmailAddressConfirmTextboxUndisplayed());
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