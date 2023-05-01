package pageObjects.nopCommerce.user;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserMyProductReviewPageObject extends BasePage{
    private WebDriver driver;

    public UserMyProductReviewPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
        return PageGeneratorManager.getUserRewardPointPage(driver);
    }
}
