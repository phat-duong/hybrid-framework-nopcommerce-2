package pageObjects.nopCommerce.user;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserAddressPageObject extends BasePage{
    private WebDriver driver;

    public UserAddressPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
        return PageGeneratorManager.getUserMyProductReviewPage(driver);
    }
}
