package pageObjects.jquery.uploadFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.uploadFile.HomePageUI;


public class HomePageObject extends BasePage{
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public void clickOnStartButton() {
        List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
        for (WebElement startButton : startButtons) {
            startButton.click();
            sleepInSecond(2);
        }
    }

    public boolean isFileLinkUpLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }

    public boolean isFileImageUpLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
        return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
    }
}