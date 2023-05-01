package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageObject extends BasePageFactory{
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Trong page factory thì page UI viết chung trong page object luôn
    //Page UI

    //CacheLookup cho phép element chỉ được lookup 1 lần duy nhất rồi sử dụng lại để tăng performance
    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()='Register']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[text()='My account']")
    private WebElement myAccountLink;


    //Page Object/ Action
    public void clickOnRegisterLink() {
        waitForElementClickable(driver, registerLink);
        clickToElement(driver, registerLink);
    }

    public void clickOnLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(driver, loginLink);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, myAccountLink);
        return isElementDisplayed(driver, myAccountLink);
    }
}