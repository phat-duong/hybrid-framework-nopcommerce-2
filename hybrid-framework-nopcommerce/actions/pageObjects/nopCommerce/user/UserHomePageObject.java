package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage{
    //biến global
    private WebDriver driver;

    //Constructor
    public UserHomePageObject(WebDriver driver) {
        //biến local
        this.driver = driver;
    }

    public UserRegisterPageObject clickOnRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);

        //C2: đưa việc khởi tạo vào trong chính hàm (action của page cũ)
        //Data type của hàm này chính là class luôn
//		return new RegisterPageObject(driver);

        //C3
        return PageGeneratorManager.getUserRegisterPage(driver);

    }

    public UserLoginPageObject clickOnLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }


    public UserCustomerInfoPageObject clickOnMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserCustomerInfoPage(driver);
    }

}
