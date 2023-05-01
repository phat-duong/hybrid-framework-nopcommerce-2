package pageObjects.facebook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;
import pageUIs.jquery.uploadFile.HomePageUI;


public class LoginPageObject extends BasePage{
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCreateNewAccountButton() {
        waitForElementClickable(driver, LoginPageUI.BUTTON_CREATE_NEW_ACCOUNT);
        clickToElement(driver, LoginPageUI.BUTTON_CREATE_NEW_ACCOUNT);
    }

    public boolean isEmailAddressTextboxDisplayed() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
    }

    public boolean isEmailAddressConfirmTextboxDisplayed() {
        return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
    }

    public void enterToEmailAddressTextbox(String string) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, string);
    }

    public void clickOnIconClose() {
        waitForElementClickable(driver, LoginPageUI.ICON_CLOSE);
        clickToElement(driver, LoginPageUI.ICON_CLOSE);
    }

    public boolean isEmailAddressConfirmTextboxUndisplayed() {
        waitForElementUndisplayed(driver, LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
        return isElementUndisplayed(driver, LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
    }

}