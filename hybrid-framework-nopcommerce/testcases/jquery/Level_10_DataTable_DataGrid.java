package jquery;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.dataTable.HomePageObject;
import pageObjects.jquery.dataTable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest{
    private WebDriver driver;
    private HomePageObject homePage;
    private List<String> actualAllCountryValues;
    private List<String> expectedAllCountryValues;

    @Parameters({"browser", "url"}) //goi den cai khoi tao driver tuong ung voi browser name
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        System.out.println("Run on " + browserName);
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }
//    @Test
    public void Table_01_Paging() {
        homePage.openPagingByPageNumber("10");
        Assert.assertTrue(homePage.isPageNumberActived("10"));

        homePage.openPagingByPageNumber("12");
        Assert.assertTrue(homePage.isPageNumberActived("12"));
    }
//    @Test
    public void Table_02_Enter_to_Header() {
        homePage.refreshCurrentPage(driver);

        homePage.enterToHeaderTextboxByLabel("Females", "777");
        homePage.enterToHeaderTextboxByLabel("Country", "Antigua and Barbuda");
        homePage.enterToHeaderTextboxByLabel("Males", "803");
        homePage.enterToHeaderTextboxByLabel("Total", "1580");
    }
//    @Test
    public void Table_03_Enter_to_Header() {
        homePage.refreshCurrentPage(driver);

        actualAllCountryValues = homePage.getValueEachRowAtAllPage();

        //đọc dữ liệu của file country.txt ra
        //lưu vào 1 List<String> = Expected Value = expectedAllCountryValues

//        Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
    }

    @Test
    public void Table_04_Enter_to_Textbox_At_Any_Row() {
        //value để nhập dữ liệu - tham số 1
        //row number: tại row nào
        //Ex: nhập vào textbox tại dòng số 3/5/2
        // column name: company/....

        homePage.enterToTextboxByColumnNameAtRowNumber("Company", "1", "abc");

        homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "1", "abc");

    }

    @Test
    public void Table_05_Action_At_Any_Row() {
        homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Japan");

        homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "1");

        homePage.clickToIconByRowNumber("1","Remove Current Row");
        homePage.sleepInSecond(2);
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