package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page UI
    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailTextbox;

    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameTextbox;

    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameTextbox;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordTextbox;

    @FindBy(xpath = "//button[@id='register-button']")
    private WebElement registerButton;

    @FindBy(xpath = "//span[@id='FirstName-error']")
    private WebElement firstNameErrorMessage;

    @FindBy(xpath = "//span[@id='LastName-error']")
    private WebElement lastNameErrorMessage;

    @FindBy(xpath = "//span[@id='Email-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//span[@id='Password-error']")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//span[@id='ConfirmPassword-error']")
    private WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registerSuccessMessage;

    @FindBy(xpath = "//a[text()='Log out']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[contains(@class,'message-error')]//li")
    private WebElement existingEmailErrorMessage;

    //Page Object
    public void inputToFirstnameTextbox(String firstname) {
        waitForElementVisible(driver, firstNameTextbox);
        sendkeyToElement(driver, firstNameTextbox, firstname);

    }

    public void inputToLastnameTextbox(String lastname) {
        waitForElementVisible(driver, lastNameTextbox);
        sendkeyToElement(driver, lastNameTextbox, lastname);

    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, emailAddress);

    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, password);

    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendkeyToElement(driver, confirmPasswordTextbox, confirmPassword);

    }

    public void clickOnRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(driver, registerButton);

    }

    public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisible(driver, confirmPasswordErrorMessage);
        return getElementText(driver, confirmPasswordErrorMessage);
    }

    public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisible(driver, passwordErrorMessage);
        return getElementText(driver, passwordErrorMessage);
    }

    public String getErrorExistingEmailMessage() {
        waitForElementVisible(driver, existingEmailErrorMessage);
        return getElementText(driver, existingEmailErrorMessage);
    }

    public void clickOnLogoutLink() {
        waitForElementClickable(driver, logoutLink);
        clickToElement(driver, logoutLink);

    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(driver, registerSuccessMessage);
    }

    public String getErrorMessageAtEmailnameTextbox() {
        waitForElementVisible(driver, emailErrorMessage);
        return getElementText(driver, emailErrorMessage);
    }

    public String getErrorMessageAtFirstnameTextbox() {
        waitForElementVisible(driver, firstNameErrorMessage);
        return getElementText(driver, firstNameErrorMessage);
    }

    public String getErrorMessageAtLastnameTextbox() {
        waitForElementVisible(driver, lastNameErrorMessage);
        return getElementText(driver, lastNameErrorMessage);
    }

}
