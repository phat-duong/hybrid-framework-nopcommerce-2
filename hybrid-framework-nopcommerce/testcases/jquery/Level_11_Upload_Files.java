package jquery;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.uploadFile.HomePageObject;
import pageObjects.jquery.uploadFile.PageGeneratorManager;


public class Level_11_Upload_Files extends BaseTest{
    private WebDriver driver;
    private HomePageObject homePage;
    String cauvongFileName = "cauvong.jpg";
    String phuongHoangLuaFileName = "phuonghoanglua.jpg";
    String[] multipleFileNames = {cauvongFileName, phuongHoangLuaFileName};

    @Parameters({"browser", "url"}) //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Upload_01_One_File_Per_Time() {
        //Step 1 - Load single file
        homePage.uploadMultipleFiles(driver, cauvongFileName);

        //Step 2 - Verify single file loaded success
        Assert.assertTrue(homePage.isFileLoadedByName(cauvongFileName));

        //Step 3 - Click on Start Button
        homePage.clickOnStartButton();

        //Step 4 - Verify single file uploaded success
        Assert.assertTrue(homePage.isFileLinkUpLoadedByName(cauvongFileName));
        Assert.assertTrue(homePage.isFileImageUpLoadedByName(cauvongFileName));
    }

    @Test
    public void Upload_02_Multiple_File_Per_Time() {
        homePage.refreshCurrentPage(driver);
        //Step 1 - Load multiple files
        homePage.uploadMultipleFiles(driver, multipleFileNames);

        //Step 2 - Verify multiple file loaded success
        Assert.assertTrue(homePage.isFileLoadedByName(cauvongFileName));
        Assert.assertTrue(homePage.isFileLoadedByName(phuongHoangLuaFileName));

        //Step 3 - Click on Start Button
        homePage.clickOnStartButton();

        //Step 4 - Verify multiple file uploaded success
        Assert.assertTrue(homePage.isFileLinkUpLoadedByName(cauvongFileName));
        Assert.assertTrue(homePage.isFileLinkUpLoadedByName(phuongHoangLuaFileName));

        Assert.assertTrue(homePage.isFileImageUpLoadedByName(cauvongFileName));
        Assert.assertTrue(homePage.isFileImageUpLoadedByName(phuongHoangLuaFileName));
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