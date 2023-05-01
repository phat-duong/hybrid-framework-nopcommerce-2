package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory{
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page UI
    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@id='Email-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailTextbox;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    private WebElement unsuccessfulErrorMessage;


    //Page Object

    public void clickOnLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, emailErrorMessage);
        return getElementText(driver, emailErrorMessage);
    }

    public void inputIntoEmailTextbox(String invalidEmail) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, invalidEmail);
    }

    public String getErrorMessageUnsuccessful() {
        waitForElementVisible(driver, unsuccessfulErrorMessage);
        return getElementText(driver, unsuccessfulErrorMessage);
    }

    public void inputIntoPasswordTextbox(String string) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, string);
    }

}
