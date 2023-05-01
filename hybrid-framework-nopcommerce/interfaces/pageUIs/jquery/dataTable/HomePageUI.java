package pageUIs.jquery.dataTable;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String TOTAL_PAGINATION_PAGE = "xpath=//ul[@class='qgrd-pagination-ul']/li";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody//tr";
    public static final String ALL_COUNTRY_COLUMN_EACH_PAGE = "xpath=//tbody//tr/td[@data-key='country']";

    public static final String HEADER_TEXTBOX_BY_LABEL_NAME = "xpath=//div[text()='%s']/ancestor::div/following-sibling::input";

    public static final String COLUMN_INDEX_BY_NAME_1 = "xpath=//tr/td[text()='%s']/preceding-sibling::td";
    public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/th[text()='%s']/preceding-sibling::th";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX_1 = "xpath=//tbody/tr[%s]/td[%s]/select";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/div/select";

    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/label/input";
    public static final String BUTTON_BY_ROW_INDEX_AND_TITLE = "xpath=//tbody/tr[%s]//button[@title='%s']";
    public static final String BUTTON_APPEND_ROW = "xpath=//tfoot[@class='ui-widget-header']//button[@title='Append Row']";
}
