package pageObjects.nopCommerce.user;

import commons.LocatorType;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserCustomerInfoPageObject extends BasePage{
    private WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCustomerInfoPageDisplayed() {
        waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
        return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
    }

//    public UserAddressPageObject openAddressPage(WebDriver driver) {

        //vi phạm nguyên tắc DRY
//        waitForElementClickable(driver, CustomerInfoPageUI.ADDRESS_LINK);
//        clickToElement(driver, CustomerInfoPageUI.ADDRESS_LINK);
//        return PageGeneratorManager.getUserAddressPage(driver);

        //k vi phạm nguyên tắc DRY thì viết hàm chung ở BasePage

//    }
}