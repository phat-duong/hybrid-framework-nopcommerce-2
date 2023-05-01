package pageObjects.jquery.dataTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.dataTable.HomePageUI;

public class HomePageObject extends BasePage{
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, headerLabel);
        sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, value, headerLabel);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, Keys.ENTER, headerLabel);
    }

    public boolean isPageNumberActived(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
    }

    public List<String> getValueEachRowAtAllPage() {
        int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION_PAGE);

        List<String> allRowValueAllPage = new ArrayList<String>();
        //duyệt qua tất cả các page number (paging)
        for (int i = 1; i <= totalPage; i++) {
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(i));

            //Get text của all row mỗi page đưa vào arraylist
            List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_COUNTRY_COLUMN_EACH_PAGE);
            for (WebElement eachRow : allRowElementEachPage) {
                allRowValueAllPage.add(eachRow.getText());
            }
        }
        //in tất cả giá trị row ra - tất cả các page
        for (String value : allRowValueAllPage) {
            System.out.println(value);
            System.out.println("---------------------------------------------------");
        }
        return allRowValueAllPage;
    }

    public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String valueToEnter) {
        //column index dựa vào tên cột
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        System.out.println(columnIndex);

        //sendkey vao textbox
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToEnter, rowNumber, String.valueOf(columnIndex));
    }

    public void selectDropdownByColumnNameAtRowNumber(String columnName, String rowNumber, String valueToDropdown) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        System.out.println(columnIndex);

        //select dropdown
        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToDropdown, rowNumber, String.valueOf(columnIndex));
    }

    public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        System.out.println(columnIndex);

        //check to checkbox
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        checkToCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
    }

    public void uncheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        System.out.println(columnIndex);

        //check to checkbox
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
        uncheckToCheckbox(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
    }

    public void clickToIconByRowNumber(String rowNumber, String buttonName) {

        //click on button
        waitForElementClickable(driver, HomePageUI.BUTTON_BY_ROW_INDEX_AND_TITLE, rowNumber, buttonName);
        clickToElement(driver, HomePageUI.BUTTON_BY_ROW_INDEX_AND_TITLE, rowNumber, buttonName);
    }
}