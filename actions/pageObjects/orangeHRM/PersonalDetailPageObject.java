package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;


public class PersonalDetailPageObject extends BasePage {
    private WebDriver driver;
    public PersonalDetailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToProfileImage() {
        waitElementClickable(driver, BasePageUI.EDIT_PROFILE_IMAGE);
        clickToElement(driver, BasePageUI.EDIT_PROFILE_IMAGE);
    }

    public String getErrorMessageAtProfileImage() {
        waitForElementVisible(driver, BasePageUI.ERROR_MESSAGE);
        return getTextElement(driver, BasePageUI.ERROR_MESSAGE);
    }

    public Dimension getProfileNaturalImageSize() {
        waitForElementVisible(driver, BasePageUI.EDIT_PROFILE_IMAGE);
        int x = Integer.parseInt(getElementDOMProperty(driver, BasePageUI.EDIT_PROFILE_IMAGE,"naturalWidth"));
        int y = Integer.parseInt(getElementDOMProperty(driver, BasePageUI.EDIT_PROFILE_IMAGE,"naturalHeight"));
        return new Dimension(x, y);
    }

}
