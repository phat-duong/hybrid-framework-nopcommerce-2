package pageObjects.nopCommerce.user;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.RewardPointPageUI;

public class UserRewardPointPageObject extends BasePage{
    private WebDriver driver;

    public UserRewardPointPageObject(WebDriver driver) {
        this.driver = driver;
    }

//    public UserAddressPageObject openAddressPage() {

        //vi phạm nguyên tắc DRY
//        waitForElementClickable(driver, RewardPointPageUI.ADDRESS_LINK);
//        clickToElement(driver, RewardPointPageUI.ADDRESS_LINK);
//        return PageGeneratorManager.getUserAddressPage(driver);

        //k vi phạm nguyên tắc DRY thì viết hàm chung ở BasePage

//    }
}
